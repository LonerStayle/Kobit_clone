package kr.loner.data.mapper

import kr.loner.data.remote.model.TickerResponse
import kr.loner.domain.model.Ticker
import kr.loner.domain.model.TickerList


object TickerMapper {
    fun tickerAllToListModel(all: Map<String, TickerResponse>) = TickerList(all.map {
        Ticker(
            it.value.timestamp,
            it.value.last,
            it.value.open,
            it.value.bid,
            it.value.ask,
            it.value.low,
            it.value.high,
            it.value.volume,
            it.value.change,
            it.value.changePercent,
            it.key.replace("_", "/").uppercase(),
        )
    })

}
