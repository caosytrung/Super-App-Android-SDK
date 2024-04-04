package com.trungcs.webminiappmanager.ui.miniAppActivity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.trungcs.webminiappmanager.model.WebMiniApp
import com.trungcs.webminiappmanager.ui.theme.SuperAppTheme

class MiniAppActivity : ComponentActivity() {
    private lateinit var url: String
    private lateinit var extraConfig: Map<String, String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        url = intent.getStringExtra(MINI_APP_URL) ?: ""
        extraConfig = bundle?.keySet()?.associateWith { bundle.getString(it) ?: "" } ?: mapOf()

        setContent {
            SuperAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MiniAppScreen(url, extraConfig) {
                        finish()
                    }
                }
            }
        }
    }

    companion object {
        private const val MINI_APP_URL = "MINI_APP_URL"
        fun start(
            context: Context,
            app: WebMiniApp,
        ) {
            val intent = Intent(context, MiniAppActivity::class.java).apply {
                val bundle = Bundle().apply {
                    for ((key, value) in app.extraConfig) {
                        putString(key, value)
                    }
                }
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(MINI_APP_URL, app.url)
                putExtras(bundle)
            }
            context.startActivity(intent)
        }
    }
}