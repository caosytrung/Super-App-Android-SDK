package com.trungcs.webminiappmanager.ui.miniAppActivity

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class MiniAppViewModel @Inject constructor() : ViewModel() {
    private val _isLoading =
        MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun setLoading(isLoading: Boolean) {
        _isLoading.value = isLoading
    }
}