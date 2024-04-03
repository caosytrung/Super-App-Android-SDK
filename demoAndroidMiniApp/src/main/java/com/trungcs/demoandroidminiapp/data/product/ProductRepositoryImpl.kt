package com.trungcs.demoandroidminiapp.data.product

import com.trungcs.base.utils.result
import com.trungcs.demoandroidminiapp.di.ProductApiAnnotation
import com.trungcs.demoandroidminiapp.remote.ProductApi
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    @ProductApiAnnotation val productApi: ProductApi,
) : ProductRepository {
    override suspend fun getProducts() = result {
        productApi.getProduct()
    }
}