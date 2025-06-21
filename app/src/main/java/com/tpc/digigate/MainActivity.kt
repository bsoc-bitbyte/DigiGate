package com.tpc.digigate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.auth.FirebaseAuth
import com.tpc.digigate.ui.navigation.AppNavDisplay
import com.tpc.digigate.ui.navigation.AuthNavDisplay
import com.tpc.digigate.ui.theme.DigiGateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val firebaseAuth = FirebaseAuth.getInstance()
            val isUserAuthenticated =
                firebaseAuth.currentUser != null && firebaseAuth.currentUser!!.isEmailVerified == true
            val isMainApp = remember { mutableStateOf(isUserAuthenticated) }
            DigiGateTheme {
                if (isMainApp.value)
                    AppNavDisplay(onSignOut = { isMainApp.value = false })
                else AuthNavDisplay(goToMainApp = { isMainApp.value = true }, context = this)
            }
        }
    }
}