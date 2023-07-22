package kr.loner.domain.model

data class Ticker(
    val timestamp: Long,
//    현재가
    val last: Double,
    val open: Double,
    val bid: Double,
    val ask: Double,
    val low: Double,
    val high: Double,
//    거래량
    val volume: Double,
//    변동 가격
    val change: Double,
//    변동률
    val changePercent: Double,
//    가상 자산명
    val currencyPair: String,
    val isFavorite:Boolean = false,
)
