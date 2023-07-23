package kr.loner.korbit.ui.ticker.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import kr.loner.korbit.R
import kr.loner.korbit.ui.ticker.TickerViewModel
import kr.loner.korbit.ui.util.UiState
import kr.loner.korbit.ui.util.compose.ErrorView
import kr.loner.korbit.ui.util.compose.LoadingView


@Composable
fun TickerScreen(viewModel: TickerViewModel = hiltViewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val uiState by viewModel.tickerList.collectAsState()

        when (uiState) {
            is UiState.Success -> {

            }

            is UiState.Loading -> {
                LoadingView(modifier = Modifier.fillMaxSize())
            }

            is UiState.Error -> {
                ErrorView(
                    stringResource(id = R.string.data_result_error),
                    modifier = Modifier.fillMaxSize(),
                    onClickRetry = {  }
                )
            }
        }
    }
}