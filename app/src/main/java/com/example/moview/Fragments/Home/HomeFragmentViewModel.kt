package com.example.moview.Fragments.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moview.data.Repository.Estreno.EstrenoRepository
import com.example.moview.data.Repository.Estreno.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val repository: EstrenoRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<HomeFragmentUiState> =
        MutableStateFlow(HomeFragmentUiState.Default)
    val uiState: StateFlow<HomeFragmentUiState> = _uiState

    fun getEstrenos() {
        viewModelScope.launch {
            _uiState.value = HomeFragmentUiState.Loading
            when(val estrenosResult = repository.getEstrenos()) {
                is Resource.Error -> {
                    _uiState.value = HomeFragmentUiState.Error(estrenosResult.message ?: "")
                }
                is Resource.Success -> {
                    _uiState.value = HomeFragmentUiState.Success(estrenosResult.data ?: listOf())
                }
            }
        }
    }
}