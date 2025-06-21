package com.tpc.digigate.domain.model

data class User(
    val uid: String,
    val email: String = "",
    val displayName: String = "",
    val isEmailVerified: Boolean,
    val photoUrl: String = ""
)
