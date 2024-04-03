package com.trungcs.superapp.home

import androidx.lifecycle.ViewModel
import com.trungcs.mini_app_handler.model.MiniApp
import com.trungcs.mini_app_handler.model.MiniAppType
import com.trungcs.superapp.data.MiniAppListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(miniAppListRepository: MiniAppListRepository) :
    ViewModel() {
    private val miniApps: List<MiniApp> = miniAppListRepository.getListOfMiniApps()

    fun getMiniAppByType(type: MiniAppType) = miniApps.firstOrNull { it.type == type }
}