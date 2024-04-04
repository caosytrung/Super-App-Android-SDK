package com.trungcs.demoandroidminiapp.ui.productlist


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.trungcs.base.widget.CenterColumn
import com.trungcs.demoandroidminiapp.R
import com.trungcs.demoandroidminiapp.remote.model.Product
import com.trungcs.demoandroidminiapp.ui.theme.MiniAppTheme

@Composable
fun ProductListScreen(viewModel: ProductListViewModel = viewModel()) {
    Scaffold(
        topBar = { TopBar(viewModel.titleFromSuperApp) },
    ) { padding ->
        Content(
            viewModel,
            modifier = Modifier.padding(padding),
        )
    }
}

@Composable
fun Content(
    viewModel: ProductListViewModel,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        val uiState by viewModel.uiState.collectAsState()

        when (val state = uiState) {
            is ProductListsUiState.Loading -> LoadingView()
            is ProductListsUiState.Error -> ErrorView()
            is ProductListsUiState.Success -> ProductList(
                exchangeToken = viewModel.exchangeToken,
                productList = state.products
            )
        }
    }
}

@Composable
fun LoadingView() {
    CenterColumn(modifier = Modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.demo_loading))
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorView() {
    CenterColumn(modifier = Modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.demo_error_fetching_product))
    }
}

@Composable
private fun ProductList(
    exchangeToken: String,
    productList: List<Product>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
) {

    Column {
        Text(text = "${stringResource(id = R.string.demo_super_app_exchange_token)}")
        LazyColumn(
            modifier = modifier,
            state = listState
        ) {
            items(productList.size, { productList[it].id }) { item ->
                Column(Modifier.fillParentMaxWidth()) {
                    ProductItem(
                        modifier = Modifier.fillParentMaxWidth(),
                        item = productList[item],
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(item: Product, modifier: Modifier) {
    val columnWidth = 80.dp
    Card(
        modifier
            .height(columnWidth)
            .padding(4.dp), RoundedCornerShape(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(all = 4.dp)
        ) {
            Box {
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = item.image)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                )
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .clip(RoundedCornerShape(100))
                )
                if (painter.state is AsyncImagePainter.State.Loading || painter.state is AsyncImagePainter.State.Error) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_cloud_download),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(columnWidth / 2)
                            .align(Alignment.Center),
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
            ) {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = item.sku)
            }

        }
    }
}


@Composable
fun TopBar(title: String, modifier: Modifier = Modifier) {
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
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(Color.White)
            )
        }
    }
}

@Preview(showBackground = true, name = "MiniApp")
@Composable
fun HomePreview() {
    MiniAppTheme {
        ProductList(
            listOf(
                Product(1, "", "Product 1", "123", ""),
                Product(2, "", "Product 2", "321", "")
            ),
        )
    }
}
