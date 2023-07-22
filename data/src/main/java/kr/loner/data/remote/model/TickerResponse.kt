package kr.loner.data.remote.model

import com.squareup.moshi.Json


data class TickerResponse(
    @Json(name = "timestamp") val timestamp: Long,
    @Json(name = "last") val last: Double,
    @Json(name = "open") val open: Double,
    @Json(name = "bid") val bid: Double,
    @Json(name = "ask") val ask: Double,
    @Json(name = "low") val low: Double,
    @Json(name = "high") val high: Double,
    @Json(name = "volume") val volume: Double,
    @Json(name = "change") val change: Double,
    @Json(name = "changePercent") val changePercent: Double
)
