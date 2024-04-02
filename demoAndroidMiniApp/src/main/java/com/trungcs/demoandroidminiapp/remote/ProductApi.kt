package com.trungcs.demoandroidminiapp.remote

import com.google.gson.GsonBuilder
import com.trungcs.demoandroidminiapp.remote.model.Product
import retrofit2.http.Body
import retrofit2.http.GET

interface ProductApi {
    @GET(Endpoint.GET_PRODUCTS)
    suspend fun getProduct(): List<Product>

    companion object {
        internal val DEFAULT_GSON = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }
}