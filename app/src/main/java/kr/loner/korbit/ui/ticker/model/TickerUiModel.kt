package kr.loner.korbit.ui.ticker.model

import kr.loner.domain.model.Ticker

data class TickerUiModel(
//    현재가
    val last: String,
    val open: String,
    val bid: String,
    val ask: String,
    val low: String,
    val high: String,
//    거래량
    val volume: String,
//    변동 가격
    val change: String,
//    변동률
    val changePercent: String,
//    가상 자산명
    val currencyPair: String,
    val isFavorite: Boolean = false,
    val growthType: GrowthStatus,
    val orgData: Ticker,
) {
    enum class GrowthStatus {
        Zero,
        Increase,
        Decrease
    }
}