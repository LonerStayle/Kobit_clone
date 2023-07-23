package kr.loner.korbit.ui.ticker.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kr.loner.korbit.ui.ticker.model.TickerListUiModel
import kr.loner.korbit.ui.ticker.model.TickerUiModel


@Composable
fun TickerListView(
    tickerListUi: TickerListUiModel,
    paddingValues: PaddingValues = PaddingValues(top = 12.dp, start = 20.dp, end = 20.dp),
    toggleFavoriteEvent: (TickerUiModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = paddingValues
    ) {
        items(tickerListUi.tickerUiList) { tickerUi ->
            Column(modifier = Modifier.padding(bottom = 12.dp)) {
                TickerItem(tickerUi = tickerUi) {
                    toggleFavoriteEvent(it)
                }
            }
        }
    }
}

@Composable
fun TickerItem(tickerUi: TickerUiModel, toggleFavoriteEvent: (TickerUiModel) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                RoundedCornerShape(8.dp)
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val favoriteIcon =
            if (tickerUi.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder

        Image(
            imageVector = favoriteIcon,
            contentDescription = "FavoriteOnOff",
            modifier = Modifier
                .size(24.dp)
                .clickable {
                    toggleFavoriteEvent(tickerUi)
                }
                .weight(0.05f),
        )

        Text(
            text = tickerUi.currencyPair,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(0.1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = tickerUi.last,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(0.15f),
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier.weight(0.1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val percentColor = when (tickerUi.growthType) {
                TickerUiModel.GrowthStatus.Increase -> Color.Red
                TickerUiModel.GrowthStatus.Decrease -> Color.Blue
                TickerUiModel.GrowthStatus.Zero -> Color.Black
            }
            Text(
                text = tickerUi.changePercent, color = percentColor,
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                text = tickerUi.change,
                style = MaterialTheme.typography.labelSmall
            )
        }
        Text(
            text = tickerUi.volume,
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(0.12f)

        )
    }
}
