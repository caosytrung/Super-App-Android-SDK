package com.trungcs.superapp.data

import com.trungcs.mini_app_bridge.model.MiniApp
import com.trungcs.superapp.data.datasource.MiniAppDataSource
import javax.inject.Inject

class MiniAppListRepositoryImpl @Inject constructor(private val dataSource: MiniAppDataSource) :
    MiniAppListRepository {
    override fun getListOfMiniApps(): List<MiniApp> {
        return dataSource.getListOfMiniApp()
    }
}