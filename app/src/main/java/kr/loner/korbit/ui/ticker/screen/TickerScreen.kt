package kr.loner.korbit.ui.ticker.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import kr.loner.korbit.R
import kr.loner.korbit.ui.ticker.TickerViewModel

private data class TabRowItem(
    val title: String,
    val screen: @Composable () -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TickerScreen() {
    val tabRowItems = listOf(
        TabRowItem(stringResource(id = R.string.ticker_tab_market)) {
            TickerMarketScreen()
        },
        TabRowItem(stringResource(id = R.string.ticker_tab_favorite)) {
            TickerFavoriteScreen()
        },
    )
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            SearchBar()
            TabRow(
                selectedTabIndex = pagerState.currentPage,
            ) {
                tabRowItems.forEachIndexed { index, tabRow ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        unselectedContentColor = Color.Gray,
                        onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                        text = {
                            Text(
                                text = tabRow.title,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    )
                }
            }

            HorizontalPager(tabRowItems.size, state = pagerState) { pos ->
                tabRowItems[pos].screen()
            }
        }
    }
}

@Composable
private fun SearchBar(viewModel: TickerViewModel = viewModel()) {
    val queryText = viewModel.searchQuery.collectAsState().value.queryText

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            imageVector = Icons.Filled.Search,
            contentDescription = "SearchIcon",
            modifier = Modifier.size(24.dp)
        )

        BasicTextField(
            value = queryText,
            onValueChange = {
                viewModel.setQueryText(it)
            },
            modifier = Modifier
                .fillMaxWidth(0.96f),
            singleLine = true,
            decorationBox = { inner ->
                if (queryText.isEmpty()) {
                    Text(
                        text = stringResource(R.string.ticker_search_hint),
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                inner()
            },
            textStyle = MaterialTheme.typography.bodyMedium,
        )
    }
}

