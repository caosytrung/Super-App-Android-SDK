package com.trungcs.demoandroidminiapp.ui.productlist


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trungcs.demoandroidminiapp.data.product.ProductRepository
import com.trungcs.demoandroidminiapp.launcher.DemoMiniAppLauncher.Companion.KEY_ARG_EXCHANGE_TOKEN
import com.trungcs.demoandroidminiapp.launcher.DemoMiniAppLauncher.Companion.KEY_ARG_TITLE_FROM_SUPER_APP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val titleFromSuperApp = savedStateHandle.get<String>(KEY_ARG_TITLE_FROM_SUPER_APP) ?: ""
    val exchangeToken = savedStateHandle.get<String>(KEY_ARG_EXCHANGE_TOKEN) ?: ""

    private val _uiState =
        MutableStateFlow<ProductListsUiState>(ProductListsUiState.Loading)
    val uiState: StateFlow<ProductListsUiState> = _uiState.asStateFlow()

    init {
        getListOfProducts()
    }

    private fun getListOfProducts() {
        viewModelScope.launch {
            val result = productRepository.getProducts()
            if (result.isFailure()) {
                _uiState.emit(ProductListsUiState.Error)
            } else {
                _uiState.emit(ProductListsUiState.Success(result.get()))
            }
        }
    }

}

