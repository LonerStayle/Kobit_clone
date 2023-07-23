package kr.loner.korbit.ui.ticker.model

data class TickerListUiModel(
    val tickerUiList: List<TickerUiModel> = emptyList(),
) {
    companion object {
        val EmptyModel = TickerListUiModel()
    }
}