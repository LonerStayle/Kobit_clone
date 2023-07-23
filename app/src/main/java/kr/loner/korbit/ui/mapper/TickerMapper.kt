package kr.loner.korbit.ui.mapper

import kr.loner.domain.model.Ticker
import kr.loner.domain.model.TickerList
import kr.loner.korbit.ui.ticker.model.TickerListUiModel
import kr.loner.korbit.ui.ticker.model.TickerUiModel
import java.text.DecimalFormat

fun TickerList.toUiModel() = TickerListUiModel(this.tickers.map(Ticker::toUiModel))

fun Ticker.toUiModel() =
    TickerUiModel(
        last.tickerTransactionAmount(),
        open.tickerTransactionAmount(),
        bid.tickerTransactionAmount(),
        ask.tickerTransactionAmount(),
        low.tickerTransactionAmount(),
        high.tickerTransactionAmount(),
        volume.toInt().toDouble().transactionAmount(amount),
        change.tickerTransactionAmount(),
        changePercent.changePercentTransDecimals(),
        currencyPair,
        isFavorite,
        changePercent.getGrowthStatus(),
        this
    )


fun Double.tickerTransactionAmount(): String = if (this > 100) {
    this.transactionAmount(amount)
} else {
    this.transactionAmount(amountWithTwoDigits)
}

private fun Double.transactionAmount(pattern: String) = DecimalFormat(pattern).format(this)

private const val amount = "#,###"

private const val amountWithTwoDigits = "#,##0.00"

private fun Double.changePercentTransDecimals(): String {
    val sign = when {
        this == 0.0 -> ""
        this > 0.0 -> "+"
        this < 0.0 -> "-"
        else -> ""
    }
    val formattedValue = String.format("%.2f", kotlin.math.abs(this))
    return "$sign$formattedValue%"
}

private fun Double.getGrowthStatus(): TickerUiModel.GrowthStatus {
    return when {
        this == 0.0 -> TickerUiModel.GrowthStatus.Zero
        this > 0.0 -> TickerUiModel.GrowthStatus.Increase
        this < 0.0 -> TickerUiModel.GrowthStatus.Decrease
        else -> TickerUiModel.GrowthStatus.Zero
    }
}
