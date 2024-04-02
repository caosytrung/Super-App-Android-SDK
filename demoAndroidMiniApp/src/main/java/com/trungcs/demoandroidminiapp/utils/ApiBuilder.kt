package com.trungcs.demoandroidminiapp.utils

import com.google.gson.GsonBuilder
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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



    /**
     * Build OkHttpClient with logging, authenticator and interceptors
     *
     * @see [logging] [authenticator] [interceptors]
     */
    fun build(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(readTimeoutInMillis, TimeUnit.MILLISECONDS)
        .writeTimeout(writeTimeoutInMillis, TimeUnit.MILLISECONDS)
        .apply { interceptors.forEach { addInterceptor(it) } }
        .apply { authenticator?.let { authenticator(it) } }
        .build()
}

internal class ApiBuilder<T> internal constructor(private val api: Class<T>) {
    private val converterBuilder = ConverterBuilder()

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
     * Build Retrofit API with a base url, json converter and a custom client
     *
     * @see [baseUrl] [converter] [client]
     */
    fun build(): T = Retrofit.Builder()
        .baseUrl(baseUrl)
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
    block: ApiBuilder<T>.() -> Unit,
): T = ApiBuilder(api).apply(block).build()