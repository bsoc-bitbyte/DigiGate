package com.tpc.digigate.ui.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tpc.digigate.domain.model.SupportedThemes
import com.tpc.digigate.domain.repository.appPreferences.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@HiltViewModel
class SettingsViewModel @Inject constructor(private val appPreferences: AppPreferences) :
    ViewModel() {
        private val _currentTheme = MutableStateFlow(SupportedThemes.SYSTEM_DEFAULT)
    val currentTheme: StateFlow<SupportedThemes> = _currentTheme

    private val _showDialogBox = MutableStateFlow(false)
    val showDialogBox: StateFlow<Boolean> = _showDialogBox

    init{
        viewModelScope.launch{
            appPreferences.appTheme.getFlow().collectLatest { SupportedThemes ->
                _currentTheme.value= SupportedThemes
                showThemeDialogBox(show = false)
            }
        }
    }

    fun themeChoice (theme: SupportedThemes){
        viewModelScope.launch {
            appPreferences.appTheme.set(theme)
        }
    }

    fun showThemeDialogBox(show: Boolean){
        _showDialogBox.value=show
    }
}