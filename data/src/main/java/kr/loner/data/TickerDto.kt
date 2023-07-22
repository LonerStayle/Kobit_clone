package kr.loner.data

import com.squareup.moshi.Json


data class TickerDto(
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "last") val last: Float,
    @Json(name = "open") val open: Float,
    @Json(name = "bid") val bid: Float,
    @Json(name = "ask") val ask: Float,
    @Json(name = "low") val low: Float,
    @Json(name = "high") val high: Float,
    @Json(name = "volume") val volume: Double,
    @Json(name = "change") val change: Float,
    @Json(name = "changePercent") val changePercent: Float,
    val currencyPair: String
)
