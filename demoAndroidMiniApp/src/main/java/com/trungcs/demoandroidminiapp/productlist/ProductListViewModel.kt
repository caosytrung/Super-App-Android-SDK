package com.trungcs.demoandroidminiapp.productlist


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trungcs.demoandroidminiapp.data.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    productRepository: ProductRepository
) : ViewModel() {

    init {
        getListOfProducts()
    }

    private fun getListOfProducts() {
        viewModelScope.launch {

        }
    }

}

