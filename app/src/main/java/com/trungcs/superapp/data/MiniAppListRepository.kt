package com.trungcs.superapp.data

import com.trungcs.mini_app_bridge.model.MiniApp

interface MiniAppListRepository {
    fun getListOfMiniApps(): List<MiniApp>
}