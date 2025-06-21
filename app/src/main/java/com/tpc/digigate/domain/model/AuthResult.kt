package com.tpc.digigate.domain.model

import com.google.firebase.auth.FirebaseAuth

sealed class AuthResult(val message: String) {

    data class Success( val msg: String) : AuthResult( msg)

    data class Error(val msg: String) : AuthResult(msg)

    data class VerificationNeeded(val email: String, val msg: String) : AuthResult(msg)


}