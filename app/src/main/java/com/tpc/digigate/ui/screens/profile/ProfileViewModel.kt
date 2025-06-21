package com.tpc.digigate.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.tpc.digigate.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(val authRepository: AuthRepository) : ViewModel() {

    fun signOut() {
        authRepository.signOut()
    }
}