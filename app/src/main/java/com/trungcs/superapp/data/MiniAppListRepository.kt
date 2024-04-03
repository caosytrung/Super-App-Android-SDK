package com.trungcs.superapp.data

import com.trungcs.mini_app_handler.model.MiniApp

interface MiniAppListRepository {
    fun getListOfMiniApps(): List<MiniApp>
}