package com.trungcs.demoandroidminiapp.launcher

import android.app.Application
import android.content.Intent
import com.trungcs.demoandroidminiapp.productlist.ProductListActivity
import com.trungcs.native_mini_app_manager.model.NativeMiniAppLauncher

class DemoMiniAppLauncher : NativeMiniAppLauncher {
    override fun create(application: Application, extraConfig: Map<String, String>): Intent {
        val intent = Intent(application, ProductListActivity::class.java)
        intent.putExtra(
            KEY_ARG_TITLE_FROM_SUPER_APP,
            extraConfig[KEY_ARG_TITLE_FROM_SUPER_APP] ?: ""
        )

        return intent
    }

    companion object {
        const val KEY_ARG_TITLE_FROM_SUPER_APP = "title"
    }
}