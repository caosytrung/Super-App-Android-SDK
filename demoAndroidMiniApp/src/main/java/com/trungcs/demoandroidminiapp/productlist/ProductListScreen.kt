package com.trungcs.demoandroidminiapp.productlist


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
import com.trungcs.demoandroidminiapp.R
import com.trungcs.demoandroidminiapp.remote.model.Product
import com.trungcs.superapp.ui.theme.MiniAppTheme
import androidx.lifecycle.viewmodel.compose.viewModel

data class ProductListsUiState(
    val products: List<Product> = listOf(),
    val isLoading: Boolean = false,
    val throwError: Boolean = false,
)

@Composable
fun ProductListScreen() {
    Scaffold(
        topBar = { TopBar() },
    ) { padding ->
        Content(
            modifier = Modifier.padding(padding),
        )
    }
}

@Composable
fun Content(
    modifier: Modifier = Modifier,
    viewModel: ProductListViewModel = viewModel(),
) {
    var uiState = viewModel.
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
                text = stringResource(id = R.string.demo_title),
                style = MaterialTheme.typography.titleLarge.copy(Color.White)
            )
        }
    }
}

@Preview(showBackground = true, name = "MiniAPp")
@Composable
fun HomePreview() {
    MiniAppTheme {
        ProductListScreen()
    }
}
