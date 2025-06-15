package com.tpc.digigate.referenceCodes

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor() : ViewModel() {

    private val _formUiState = MutableStateFlow(FormUIState())
    val formUiState: StateFlow<FormUIState> = _formUiState.asStateFlow()

    fun nameInput(name: String) {
        _formUiState.update {
            it.copy(name)
        }
    }

    fun onSubmitClicked(){

    }
}