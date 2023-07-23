package kr.loner.korbit.ui.ticker.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kr.loner.domain.usecase.ticker.SearchTickerListUseCase
import kr.loner.korbit.R
import kr.loner.korbit.ui.ticker.TickerViewModel
import kr.loner.korbit.ui.ticker.model.TickerListUiModel
import kr.loner.korbit.ui.ticker.util.TickerListView
import kr.loner.korbit.ui.util.UiState
import kr.loner.korbit.ui.util.compose.ErrorView
import kr.loner.korbit.ui.util.compose.LoadingView
import kr.loner.korbit.ui.util.data


@Composable
fun TickerMarketScreen(viewModel: TickerViewModel = hiltViewModel()) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        val uiState by viewModel.tickerList.collectAsState()

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
    val tickerList =
        viewModel.tickerList.collectAsState().value.data ?: TickerListUiModel.EmptyModel
    val selectOrder = viewModel.searchQuery.collectAsState().value.order
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 18.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            FilterItem(
                isFilterUpUse = selectOrder == SearchTickerListUseCase.Order.CurrencyPairASC,
                isFilterDownUse = selectOrder == SearchTickerListUseCase.Order.CurrencyPairDESC,
                text = "가상자산명",
                modifier = Modifier.fillMaxWidth(0.34f),
                arrowUpEvent = { viewModel.setOrder(SearchTickerListUseCase.Order.CurrencyPairASC) },
                arrowDownEvent = { viewModel.setOrder(SearchTickerListUseCase.Order.CurrencyPairDESC) }
            )
            FilterItem(
                isFilterUpUse = selectOrder == SearchTickerListUseCase.Order.LastASC,
                isFilterDownUse = selectOrder == SearchTickerListUseCase.Order.LastDESC,
                text = "현재가",
                modifier = Modifier.padding(end = 12.dp),
                arrowUpEvent = { viewModel.setOrder(SearchTickerListUseCase.Order.LastASC) },
                arrowDownEvent = { viewModel.setOrder(SearchTickerListUseCase.Order.LastDESC) }
            )
            FilterItem(
                isFilterUpUse = selectOrder == SearchTickerListUseCase.Order.ChangePercentASC,
                isFilterDownUse = selectOrder == SearchTickerListUseCase.Order.ChangePercentDESC,
                text = "24시간",
                modifier = Modifier.padding(end = 12.dp),
                arrowUpEvent = { viewModel.setOrder(SearchTickerListUseCase.Order.ChangePercentASC) },
                arrowDownEvent = { viewModel.setOrder(SearchTickerListUseCase.Order.ChangePercentDESC) }
            )
            FilterItem(
                isFilterUpUse = selectOrder == SearchTickerListUseCase.Order.VolumeASC,
                isFilterDownUse = selectOrder == SearchTickerListUseCase.Order.VolumeDESC,
                text = "거래대금",
                arrowUpEvent = { viewModel.setOrder(SearchTickerListUseCase.Order.VolumeASC) },
                arrowDownEvent = { viewModel.setOrder(SearchTickerListUseCase.Order.VolumeDESC) }
            )
        }
        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier.padding(top = 12.dp, start = 20.dp, end = 20.dp)
        )

        TickerListView(tickerList) {
            viewModel.toggleFavoriteTicker(it.orgData)
        }
    }
}


@Composable
private fun FilterItem(
    isFilterUpUse: Boolean,
    isFilterDownUse: Boolean,
    text: String,
    modifier: Modifier = Modifier,
    arrowUpEvent: () -> Unit,
    arrowDownEvent: () -> Unit,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
        )
        Column {
            val arrowUpRes =
                if (isFilterUpUse) R.drawable.ic_arrow_up_select else R.drawable.ic_arrow_up
            Image(
                painter = painterResource(id = arrowUpRes),
                contentDescription = "ArrowUp",
                modifier = Modifier
                    .size(14.dp)
                    .clickable {
                        arrowUpEvent()
                    }
            )

            Spacer(modifier = Modifier.size(4.dp))

            val arrowDownRes =
                if (isFilterDownUse) R.drawable.ic_arrow_down_select else R.drawable.ic_arrow_down
            Image(painter = painterResource(id = arrowDownRes),
                contentDescription = "ArrowDown",
                modifier = Modifier
                    .size(14.dp)
                    .clickable {
                        arrowDownEvent()
                    })

        }

    }
}


