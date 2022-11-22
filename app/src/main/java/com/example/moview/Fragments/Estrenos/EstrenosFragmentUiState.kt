package com.example.moview.Fragments.Estrenos

import com.example.moview.data.local.entity.TituloEstreno

sealed interface EstrenosFragmentUiState {
    data class Success(val estrenos: List<TituloEstreno>): EstrenosFragmentUiState
    object Loading: EstrenosFragmentUiState
    object Default: EstrenosFragmentUiState
    data class Error(val message: String): EstrenosFragmentUiState
}