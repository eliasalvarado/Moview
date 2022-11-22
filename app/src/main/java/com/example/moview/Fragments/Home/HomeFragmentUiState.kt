package com.example.moview.Fragments.Home

import com.example.moview.data.local.entity.TituloEstreno

sealed interface HomeFragmentUiState {
    data class Success(val estrenos: List<TituloEstreno>): HomeFragmentUiState
    object Loading: HomeFragmentUiState
    object Default: HomeFragmentUiState
    data class Error(val message: String): HomeFragmentUiState
}