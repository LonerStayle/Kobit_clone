package kr.loner.kobit.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kr.loner.domain.usecase.GetTickerListUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getTickerListUseCase: GetTickerListUseCase
    ) : ViewModel() {
        init {
            viewModelScope.launch {
                val result = getTickerListUseCase().getOrNull()
            }
        }
}