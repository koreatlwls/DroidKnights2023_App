package com.droidknights.app2023.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidknights.app2023.core.domain.usecase.GetSponsorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getSponsorsUseCase: GetSponsorsUseCase,
) : ViewModel() {

    val sponsorsUiState: StateFlow<SponsorsUiState> = flow { emit(getSponsorsUseCase()) }
        .map { SponsorsUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SponsorsUiState(emptyList())
        )
}
