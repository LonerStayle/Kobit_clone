package kr.loner.korbit.ui.ticker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kr.loner.domain.model.Ticker
import kr.loner.domain.model.TickerList
import kr.loner.domain.usecase.ticker.SearchTickerListUseCase
import kr.loner.domain.usecase.ticker.ToggleFavoriteUseCase
import kr.loner.korbit.ui.mapper.toUiModel
import kr.loner.korbit.ui.ticker.model.TickerListUiModel
import kr.loner.korbit.ui.ticker.model.TickerUiModel
import kr.loner.korbit.ui.util.UiState
import kr.loner.korbit.ui.util.data
import kr.loner.korbit.ui.util.exception
import kr.loner.korbit.ui.util.isError
import javax.inject.Inject

@HiltViewModel
class TickerViewModel @Inject constructor(
    private val searchTickerListUseCase: SearchTickerListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow(SearchTickerListUseCase.SearchQuery())
    val searchQuery: StateFlow<SearchTickerListUseCase.SearchQuery> = _searchQuery

    @OptIn(ExperimentalCoroutinesApi::class)
    private val searchTickerListFlow: Flow<TickerList> = searchQuery.flatMapLatest { query ->
        searchTickerListUseCase(query)
    }

    private val _tickerList = MutableStateFlow<UiState<TickerListUiModel>>(UiState.Loading)
    val tickerList = _tickerList.asStateFlow()

    val favoriteList: StateFlow<UiState<TickerListUiModel>> = tickerList.map { uiState ->
        if (uiState.isError) {
            UiState.Error(
                uiState.exception ?: throw NullPointerException("Exception Data is Empty")
            )
        } else {
            val listUiModel = uiState.data ?: return@map UiState.Loading
            UiState.Success(
                listUiModel.copy(tickerUiList = listUiModel.tickerUiList.filter(TickerUiModel::isFavorite))
            )
        }
    }.catch {
        UiState.Error(Exception(it))
    }.stateIn(viewModelScope, SharingStarted.Eagerly, UiState.Loading)

    init {
        collectTickerList()
    }

    private fun collectTickerList() {
        viewModelScope.launch {
            searchTickerListFlow.catch {
                _tickerList.value = UiState.Error(Exception(it))
            }.collectLatest { list ->
                _tickerList.value = UiState.Success(list.toUiModel())
            }
        }
    }

    fun refreshList() {
        _tickerList.value = UiState.Loading
        collectTickerList()
    }

    fun setQueryText(text: String) {
        _searchQuery.value = searchQuery.value.copy(queryText = text)
    }

    fun setOrder(order: SearchTickerListUseCase.Order) {
        _searchQuery.value = searchQuery.value.copy(order = order)
    }

    fun toggleFavoriteTicker(ticker: Ticker) = viewModelScope.launch {
        toggleFavoriteUseCase(ticker)
    }


}