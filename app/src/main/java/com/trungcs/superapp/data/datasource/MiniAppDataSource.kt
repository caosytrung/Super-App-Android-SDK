package com.trungcs.superapp.data.datasource

import com.trungcs.mini_app_handler.model.MiniApp

interface MiniAppDataSource {
    fun getListOfMiniApp(): List<MiniApp>
}