package com.example.moview.Fragments.Estrenos

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
class EstrenosFragmentViewModel @Inject constructor(
    private val repository: EstrenoRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<EstrenosFragmentUiState> =
        MutableStateFlow(EstrenosFragmentUiState.Default)
    val uiState: StateFlow<EstrenosFragmentUiState> = _uiState

    fun getEstrenos() {
        viewModelScope.launch {
            _uiState.value = EstrenosFragmentUiState.Loading
            when(val estrenosResult = repository.getEstrenos()) {
                is Resource.Error -> {
                    _uiState.value = EstrenosFragmentUiState.Error(estrenosResult.message ?: "")
                }
                is Resource.Success -> {
                    _uiState.value = EstrenosFragmentUiState.Success(estrenosResult.data ?: listOf())
                }
            }
        }
    }
}