package kr.loner.korbit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.loner.domain.model.Ticker
import kr.loner.domain.usecase.ticker.SearchListUseCase
import kr.loner.domain.usecase.ticker.ToggleFavoriteUseCase
import kr.loner.korbit.ui.mapper.toUiModel
import kr.loner.korbit.ui.ticker.TickerListUiModel
import kr.loner.korbit.ui.util.UiState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val searchListUseCase: SearchListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _tickerList = MutableStateFlow<UiState<TickerListUiModel>>(UiState.Loading)
    val tickerList: StateFlow<UiState<TickerListUiModel>> = _tickerList

    init {
        getTickerList()
    }

    private fun getTickerList() = viewModelScope.launch {
        searchListUseCase(SearchListUseCase.SearchQuery()).catch {
            _tickerList.value = UiState.Error(Exception(it))
        }.collectLatest {
            _tickerList.value = UiState.Success(it.toUiModel())
        }
    }

    fun isFavoriteTicker(ticker: Ticker) = viewModelScope.launch {
        toggleFavoriteUseCase(ticker)
    }

}