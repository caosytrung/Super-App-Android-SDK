package com.trungcs.superapp.ui.home

import androidx.lifecycle.ViewModel
import com.trungcs.mini_app_bridge.MiniAppBridge
import com.trungcs.mini_app_bridge.model.MiniApp
import com.trungcs.mini_app_bridge.model.MiniAppType
import com.trungcs.superapp.data.MiniAppListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    miniAppListRepository: MiniAppListRepository,
    private var miniAppManager: MiniAppBridge,
) :
    ViewModel() {
    private val miniApps: List<MiniApp> = miniAppListRepository.getListOfMiniApps()

    private fun getMiniAppByType(type: MiniAppType) = miniApps.firstOrNull { it.type == type }

    fun openNativeMiniApp() {
        val nativeMiniApp = getMiniAppByType(MiniAppType.NATIVE)
        nativeMiniApp?.let { miniAppManager.startApp(it) }
    }

    fun openWebMiniApp() {
        val nativeMiniApp = getMiniAppByType(MiniAppType.WEB)
        nativeMiniApp?.let { miniAppManager.startApp(it) }
    }

    fun openFlutterMiniApp() {

    }
}