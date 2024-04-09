package com.trungcs.superapp.data.datasource

import com.trungcs.mini_app_bridge.model.MiniApp
import com.trungcs.native_mini_app_bridge.model.NativeMiniApp
import com.trungcs.webminiappmanager.model.WebMiniApp
import javax.inject.Inject

class DemoMiniAppDataSource @Inject constructor() : MiniAppDataSource {
    private val demoExtraConfig =
        mapOf("title" to "Mini App From SuperApp", "exchangeToken" to "SuperAppToken123")

    override fun getListOfMiniApp(): List<MiniApp> {
        val demoNativeMiniApp = NativeMiniApp(
            miniAppClassName = "com.trungcs.demoandroidminiapp.launcher.DemoMiniAppLauncher",
            extraConfig = demoExtraConfig
        )

        val demoWebMiniApp = WebMiniApp(
            url = "https://mini-app-web.web.app",
            extraConfig = demoExtraConfig
        )

        return listOf(
            demoNativeMiniApp,
            demoWebMiniApp
        )
    }

}