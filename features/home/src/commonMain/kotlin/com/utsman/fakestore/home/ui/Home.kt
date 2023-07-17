package com.utsman.fakestore.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import com.utsman.core.StateEvent
import com.utsman.core.onFailure
import com.utsman.core.onIdle
import com.utsman.core.onLoading
import com.utsman.core.onSuccess
import com.utsman.fakestore.home.domain.Product
import com.utsman.fakestore.home.viewmodel.rememberHomeViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Home() {
    val homeViewModel = rememberHomeViewModel()
    val productListState by homeViewModel.productList.collectAsState()

    val isLoading by derivedStateOf {
        productListState is StateEvent.Loading
    }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isLoading,
        onRefresh = {
            homeViewModel.getProduct()
        }
    )

    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize()
                .pullRefresh(pullRefreshState)
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                with(productListState) {
                    onIdle {
                        HomeIdle { homeViewModel.getProduct() }
                    }
                    onLoading {
                        HomeLoading()
                    }
                    onSuccess { productList ->
                        HomeSuccess(productList)
                    }
                    onFailure { throwable ->
                        HomeFailure(throwable.message.orEmpty())
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun HomeSuccess(products: List<Product>) {
    LazyColumn {
        items(products) {
            Row(modifier = Modifier.padding(6.dp)) {
                val painter = rememberImagePainter(it.imageUrl)
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painter,
                    contentDescription = null
                )
                Text(it.name)
            }
        }
    }
}

@Composable
fun HomeLoading() {
    CircularProgressIndicator()
}

@Composable
fun HomeFailure(message: String) {
    Text(message, color = Color.Red)
}

@Composable
fun HomeIdle(action: () -> Unit) {
    Button(onClick = {
        action.invoke()
    }) {
        Text("get products")
    }
}