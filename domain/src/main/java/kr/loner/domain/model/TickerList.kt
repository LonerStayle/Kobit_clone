package kr.loner.domain.model

data class TickerList(
    val tickers: List<Ticker>
) {
    companion object {
        val emptyTickerList = TickerList(emptyList())
    }
}