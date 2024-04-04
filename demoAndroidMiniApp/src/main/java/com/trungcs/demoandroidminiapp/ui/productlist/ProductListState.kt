package com.trungcs.demoandroidminiapp.ui.productlist

import com.trungcs.demoandroidminiapp.remote.model.Product

sealed class ProductListsUiState {
    data object Loading : ProductListsUiState()
    data object Error : ProductListsUiState()
    data class Success(val products: List<Product> = listOf()) :
        ProductListsUiState()
}


