package com.trungcs.superapp.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trungcs.base.widget.CenterColumn
import com.trungcs.superapp.R
import com.trungcs.superapp.ui.theme.SuperAppTheme

@Composable
fun HomeScreen(
    openNativeMiniApp: () -> Unit,
    openWebMiniApp: () -> Unit,
    openFlutterMiniApp: () -> Unit,
) {
    Scaffold(
        topBar = { TopBar() },
    ) { padding ->
        MiniApps(
            modifier = Modifier.padding(padding),
            openNativeMiniApp = openNativeMiniApp,
            openWebMiniApp = openWebMiniApp,
            openFlutterMiniApp = openFlutterMiniApp,
        )
    }
}

@Composable
fun MiniApps(
    modifier: Modifier = Modifier,
    openNativeMiniApp: () -> Unit,
    openWebMiniApp: () -> Unit,
    openFlutterMiniApp: () -> Unit,
) {

    CenterColumn(modifier = modifier.fillMaxSize()) {
        ElevatedButton(
            onClick = openNativeMiniApp
        ) {
            Text(text = stringResource(id = R.string.home_native_mini_app))
        }

        Spacer(modifier = Modifier.height(12.dp))

        ElevatedButton(
            onClick = openWebMiniApp
        ) {
            Text(text = stringResource(id = R.string.home_web_mini_app))
        }

        Spacer(modifier = Modifier.height(12.dp))

        ElevatedButton(
            onClick = openFlutterMiniApp
        ) {
            Text(text = stringResource(id = R.string.home_flutter_mini_app))
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
    ) {
        Row(
            modifier = modifier.background(MaterialTheme.colorScheme.primary),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(id = R.string.home_list_of_mini_apps),
                style = MaterialTheme.typography.titleLarge.copy(Color.White)
            )
        }
    }
}

@Preview(showBackground = true, name = "XXXX")
@Composable
fun HomePreview() {
    SuperAppTheme {
        HomeScreen({}, {}, {})
    }
}
