package com.viettel.ekyc.utils

import com.google.gson.GsonBuilder
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


internal class ConverterBuilder internal constructor() {

    /**
     * Custom Json Converter Factory
     *
     * @see [Converter.Factory]
     */
    var factory: Converter.Factory? = null

    /**
     * Create Retrofit Json Converter Factory
     *
     * If client doesn't provide a custom [factory], this builder class will create one using Gson
     *
     * @see [GsonConverterFactory] [GsonBuilder]
     */
    fun build(): Converter.Factory = factory ?: GsonConverterFactory.create(
        GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    )
}

internal class LoggingBuilder internal constructor() {

    /**
     * Http Logging Level. Default is NONE which means no logs
     *
     * @see [HttpLoggingInterceptor.Level]
     */
    var level: HttpLoggingInterceptor.Level = NONE

    /**
     * Create Http Logging which is an OkHttp Interceptor and apply logging level
     *
     * @see [HttpLoggingInterceptor] [level]
     */
    fun build(): Interceptor = HttpLoggingInterceptor().apply {
        level = this@LoggingBuilder.level
    }
}

internal class ClientBuilder internal constructor() {

    /**
     * All OkHttp Interceptors that will be added into OkHttpClient
     *
     * @see Interceptor
     */
    var interceptors: List<Interceptor> = listOf()

    var readTimeoutInMillis: Long = 10_000

    var writeTimeoutInMillis: Long = 10_000

    /**
     * The OkHttp Authenticator that will be used by OkHttpClient for authentication
     *
     * @see Authenticator
     */
    var authenticator: Authenticator? = null

    private val loggingBuilder = LoggingBuilder()

    /**
     * Config logging got OkHttp
     *
     * @see [LoggingBuilder]
     */
    fun logging(block: LoggingBuilder.() -> Unit) {
        loggingBuilder.apply(block)
    }

    /**
     * Build OkHttpClient with logging, authenticator and interceptors
     *
     * @see [logging] [authenticator] [interceptors]
     */
    fun build(): OkHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(loggingBuilder.build())
        .readTimeout(readTimeoutInMillis, TimeUnit.MILLISECONDS)
        .writeTimeout(writeTimeoutInMillis, TimeUnit.MILLISECONDS)
        .apply { interceptors.forEach { addInterceptor(it) } }
        .apply { authenticator?.let { authenticator(it) } }
        .build()
}

internal class ApiBuilder<T> internal constructor(private val api: Class<T>) {
    private val converterBuilder = ConverterBuilder()
    private val clientBuilder = ClientBuilder()

    /**
     * Base url of api
     *
     * @see [Retrofit.Builder.baseUrl]
     */
    var baseUrl: String = ""

    /**
     * Json converter builder
     *
     * @see [ConverterBuilder]
     */
    fun converter(block: ConverterBuilder.() -> Unit) {
        converterBuilder.apply(block)
    }

    /**
     * Http Client builder
     *
     * @see [ClientBuilder]
     */
    fun client(block: ClientBuilder.() -> Unit) {
        clientBuilder.apply(block)
    }

    /**
     * Build Retrofit API with a base url, json converter and a custom client
     *
     * @see [baseUrl] [converter] [client]
     */
    fun build(): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(clientBuilder.build())
        .addConverterFactory(converterBuilder.build())
        .build()
        .create(api)

}

/**
 * Create Retrofit API using Kotlin DSL
 *
 * @param api Retrofit API Interface
 * @param block builder block
 *
 * @return Retrofit API which is built with applied [block]
 *
 * @see [Retrofit] [Retrofit.Builder]
 */
internal fun <T> apiBuilder(
    api: Class<T>,
    block: ApiBuilder<T>.() -> Unit
): T = ApiBuilder(api).apply(block).build()