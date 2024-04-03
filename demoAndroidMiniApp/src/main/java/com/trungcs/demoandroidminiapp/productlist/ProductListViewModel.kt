package com.trungcs.demoandroidminiapp.productlist


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trungcs.demoandroidminiapp.data.product.ProductRepository
import com.trungcs.demoandroidminiapp.launcher.DemoMiniAppLauncher.Companion.KEY_ARG_TITLE_FROM_SUPER_APP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val titleFromSuperApp = savedStateHandle.get<String>(KEY_ARG_TITLE_FROM_SUPER_APP) ?: ""

    init {
        getListOfProducts()
    }

    private fun getListOfProducts() {
        viewModelScope.launch {

        }
    }

}

