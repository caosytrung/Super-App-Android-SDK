package com.trungcs.superapp.ui.home

import androidx.lifecycle.ViewModel
import com.trungcs.mini_app_handler.MiniAppManager
import com.trungcs.mini_app_handler.model.MiniApp
import com.trungcs.mini_app_handler.model.MiniAppType
import com.trungcs.superapp.data.MiniAppListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val miniAppListRepository: MiniAppListRepository,
    private var miniAppManager: MiniAppManager,
) :
    ViewModel() {
    private val miniApps: List<MiniApp> = miniAppListRepository.getListOfMiniApps()

    private fun getMiniAppByType(type: MiniAppType) = miniApps.firstOrNull { it.type == type }

    fun openNativeMiniApp() {
        val nativeMiniApp = getMiniAppByType(MiniAppType.NATIVE)
        nativeMiniApp?.let { miniAppManager.startApp(it) }
    }

    fun openWebMiniApp() {

    }

    fun openFlutterMiniApp() {

    }
}