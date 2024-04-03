package com.trungcs.superapp.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.trungcs.mini_app_handler.MiniAppManager
import com.trungcs.mini_app_handler.model.MiniAppType
import com.trungcs.superapp.ui.theme.SuperAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    @Inject
    lateinit var miniAppManager: MiniAppManager

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(::openNativeMiniApp, ::openWebMiniApp, ::openFlutterMiniApp)
                }
            }
        }
    }

    private fun openNativeMiniApp() {
        val nativeMiniApp = homeViewModel.getMiniAppByType(MiniAppType.NATIVE)
        nativeMiniApp?.let { miniAppManager.startApp(it) }
    }

    private fun openWebMiniApp() {

    }

    private fun openFlutterMiniApp() {

    }

}
