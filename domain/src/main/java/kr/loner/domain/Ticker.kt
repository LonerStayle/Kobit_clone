package kr.loner.domain

data class Ticker(
    val timestamp: Long,
    val last: Float,
    val open: Float,
    val bid: Float,
    val ask: Float,
    val low: Float,
    val high: Float,
    val volume: Double,
    val change: Float,
    val changePercent: Float,
    val currencyPair: String
)
