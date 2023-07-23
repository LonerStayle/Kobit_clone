package kr.loner.korbit.ui.ticker.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import kr.loner.korbit.R
import kr.loner.korbit.ui.ticker.TickerViewModel
import kr.loner.korbit.ui.ticker.model.TickerListUiModel
import kr.loner.korbit.ui.ticker.util.TickerListView
import kr.loner.korbit.ui.util.UiState
import kr.loner.korbit.ui.util.compose.ErrorView
import kr.loner.korbit.ui.util.compose.LoadingView
import kr.loner.korbit.ui.util.data


@Composable
fun TickerFavoriteScreen(viewModel: TickerViewModel = hiltViewModel()) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val uiState by viewModel.favoriteList.collectAsState()
        when (uiState) {
            is UiState.Success -> {
                ContentView()
            }

            is UiState.Loading -> {
                LoadingView(modifier = Modifier.fillMaxSize())
            }

            is UiState.Error -> {
                ErrorView(
                    stringResource(id = R.string.data_result_error),
                    modifier = Modifier.fillMaxSize(),
                    onClickRetry = { viewModel.refreshList() }
                )
            }
        }
    }
}

@Composable
private fun ContentView(viewModel: TickerViewModel = hiltViewModel()) {
    val tickerUiList =
        viewModel.favoriteList.collectAsState().value.data ?: TickerListUiModel.EmptyModel
    Column(modifier = Modifier.fillMaxSize()) {
        TickerListView(tickerListUi = tickerUiList) {
            viewModel.toggleFavoriteTicker(it.orgData)
        }
    }
}

