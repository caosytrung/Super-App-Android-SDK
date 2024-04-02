package com.trungcs.demoandroidminiapp.data.product

import com.trungcs.base.utils.Result
import com.trungcs.demoandroidminiapp.remote.model.Product

interface ProductRepository {
    suspend fun getProducts(): Result<List<Product>, Throwable>
}