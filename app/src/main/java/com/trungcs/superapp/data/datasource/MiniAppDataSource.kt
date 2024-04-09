package com.trungcs.superapp.data.datasource

import com.trungcs.mini_app_bridge.model.MiniApp

interface MiniAppDataSource {
    fun getListOfMiniApp(): List<MiniApp>
}