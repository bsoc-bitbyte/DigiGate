package com.tpc.digigate.ui.screens.settings

import androidx.lifecycle.ViewModel
import com.tpc.digigate.domain.repository.appPreferences.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(private val appPreferences: AppPreferences) :
    ViewModel() {

}