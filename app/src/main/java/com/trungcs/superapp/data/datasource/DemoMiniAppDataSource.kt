package com.trungcs.superapp.data.datasource

import com.trungcs.mini_app_handler.model.MiniApp
import com.trungcs.native_mini_app_manager.model.NativeMiniApp
import com.trungcs.webminiappmanager.model.WebMiniApp
import javax.inject.Inject

class DemoMiniAppDataSource @Inject constructor() : MiniAppDataSource {
    private val demoExtraConfig =
        mapOf("title" to "Mini App From SuperApp", "exchangeToken" to "SuperAppToken123")

    override fun getListOfMiniApp(): List<MiniApp> {
        return listOf(
            NativeMiniApp(
                miniAppClassName = "com.trungcs.demoandroidminiapp.launcher.DemoMiniAppLauncher",
                extraConfig = demoExtraConfig
            ),
            WebMiniApp(
                url = "https://tiki.vn/thiet-bi-kts-phu-kien-so/c1815",
                extraConfig = demoExtraConfig
            )
        )
    }

}