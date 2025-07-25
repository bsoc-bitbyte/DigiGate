package com.tpc.digigate

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.google.firebase.auth.FirebaseAuth
import com.tpc.digigate.domain.model.SupportedThemes
import com.tpc.digigate.domain.repository.appPreferences.AppPreferences
import com.tpc.digigate.ui.navigation.AppNavDisplay
import com.tpc.digigate.ui.navigation.AuthNavDisplay
import com.tpc.digigate.ui.screens.settings.Settings
import com.tpc.digigate.ui.theme.DigiGateTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appPreferences: AppPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val intentData = intent?.data
        val mode = intentData?.getQueryParameter("mode")
        val oobCode = intentData?.getQueryParameter("oobCode")

        setContent {
            val firebaseAuth = FirebaseAuth.getInstance()
            val isUserAuthenticated =
                firebaseAuth.currentUser != null && firebaseAuth.currentUser!!.isEmailVerified
            val isMainApp = remember { mutableStateOf(isUserAuthenticated) }

            val themeFlow: SupportedThemes = appPreferences.appTheme.getFlow()
                .collectAsState(initial = SupportedThemes.SYSTEM_DEFAULT).value
            val darkTheme = when (themeFlow) {
                SupportedThemes.LIGHT -> false
                SupportedThemes.DARK -> true
                SupportedThemes.SYSTEM_DEFAULT -> isSystemInDarkTheme()
                else -> false
            }

            DigiGateTheme(darkTheme = darkTheme) {
                if (isMainApp.value) {
                    AppNavDisplay(onSignOut = { isMainApp.value = false })
                } else {
                    AuthNavDisplay(
                        goToMainApp = { isMainApp.value = true },
                        context = this,
                        mode = mode,
                        oobCode = oobCode
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        recreate()
    }
}
