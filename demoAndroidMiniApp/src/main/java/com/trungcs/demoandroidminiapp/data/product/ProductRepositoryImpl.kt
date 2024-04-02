package com.trungcs.demoandroidminiapp.data.product

import com.trungcs.base.utils.Result
import com.trungcs.base.utils.result
import com.trungcs.demoandroidminiapp.remote.ProductApi
import com.trungcs.demoandroidminiapp.remote.model.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    val productApi: ProductApi,
) : ProductRepository {
    override suspend fun getProducts() = result {
        productApi.getProduct()
    }
}