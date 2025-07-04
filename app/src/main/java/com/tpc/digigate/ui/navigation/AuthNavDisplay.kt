package com.tpc.digigate.ui.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.google.firebase.auth.FirebaseAuth
import com.tpc.digigate.ui.screens.authentication.emailSentConfirmation.EmailSentConfirmationLayout
import com.tpc.digigate.ui.screens.authentication.emailVerification.EmailVerificationScreen
import com.tpc.digigate.ui.screens.authentication.emailVerified.EmailVerifiedScreen
import com.tpc.digigate.ui.screens.authentication.forgetPassword.ForgetPasswordScreenLayout
import com.tpc.digigate.ui.screens.authentication.login.LoginScreenLayout
import com.tpc.digigate.ui.screens.authentication.register.RegisterScreenLayout
import com.tpc.digigate.ui.screens.onboarding.OnboardingPagerScreen

@Composable
fun AuthNavDisplay(
    goToMainApp: () -> Unit,
    context: Context,
    mode: String? = null,
    oobCode: String? = null
) {
    val backStack = remember {
        if (mode == "verifyEmail" && !oobCode.isNullOrBlank()) {
            mutableStateListOf(
                AuthScreen.Login,
                AuthScreen.EmailVerification
            )
        } else if (mode == "resetPassword" && !oobCode.isNullOrBlank()) {
            mutableStateListOf(
                AuthScreen.Login,
                AuthScreen.ForgetPasswordScreen
            )
        } else {
            mutableStateListOf<AuthScreen>(AuthScreen.OnBoarding)
        }
    }
    val firebaseAuth = FirebaseAuth.getInstance()
    val linkChecked = remember { mutableStateOf(false) }
    val userAuthentication = remember { mutableStateOf(false) }
    val verifyingPeriod = remember { mutableStateOf(false) }
    val isDone = remember { mutableStateOf(false) }

    if (!linkChecked.value && mode == "verifyEmail" && !oobCode.isNullOrBlank()) {
        linkChecked.value = true
        verifyingPeriod.value = true
        firebaseAuth.applyActionCode(oobCode)
            .addOnCompleteListener { result ->
                verifyingPeriod.value = false
                if (result.isSuccessful) {
                    firebaseAuth.currentUser?.reload()?.addOnCompleteListener {
                        userAuthentication.value =
                            firebaseAuth.currentUser?.isEmailVerified == true
                        if (userAuthentication.value) {
                            isDone.value = true
                            backStack += AuthScreen.EmailVerified(
                                "Email verified successfully",
                                "Your email has been verified!",
                            )
                        } else {
                            backStack += AuthScreen.EmailVerified(
                                "Email Verification Failed",
                                "We couldn't confirm your email verification. Please try again."
                            )
                        }
                    }
                } else {
                    backStack += AuthScreen.EmailVerified(
                        "Invalid or expired verification link",
                        "This link is no longer valid. Please request a new one or use the latest link sent to your email."
                    )
                }
            }
    }
    if (!linkChecked.value && mode == "resetPassword" && !oobCode.isNullOrBlank()) {
        linkChecked.value = true
    }


    Scaffold { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                entryProvider = entryProvider {
                    entry<AuthScreen.OnBoarding> {
                        OnboardingPagerScreen(onFinished = {
                            backStack.removeLastOrNull()
                            backStack.add(AuthScreen.Register)
                        })
                    }
                    entry<AuthScreen.Register> {
                        RegisterScreenLayout(
                            onHaveAccount = {
                                backStack.removeLastOrNull()
                                backStack.add(AuthScreen.Login)
                            },
                            goToMainApp = {
                                backStack.clear()
                                goToMainApp()
                            },
                            context = context,
                            goToEmailVerificationScreen = {
                                backStack.add(AuthScreen.EmailVerification)
                            }
                        )
                    }
                    entry<AuthScreen.Login> {
                        LoginScreenLayout(
                            onCreateAccount = {
                                backStack.removeLastOrNull()
                                backStack.add(AuthScreen.Register)
                            },
                            goToMainApp = {
                                backStack.clear()
                                goToMainApp()
                            },
                            context = context,
                            goToEmailVerificationScreen = {
                                backStack.add(AuthScreen.EmailVerification)
                            },
                            onForgetPasswordClicked = {
                                backStack.add(AuthScreen.ForgetPasswordScreen)
                            }
                        )
                    }
                    entry<AuthScreen.ForgetPasswordScreen> {
                        ForgetPasswordScreenLayout(
                            onBackClicked = { backStack.removeLastOrNull() },
                            onResetLinkSendClicked = { email ->
                                backStack += AuthScreen.EmailSentConfirmation(email)
                            }
                        )
                    }
                    entry<AuthScreen.EmailSentConfirmation> { tempEmail ->
                        EmailSentConfirmationLayout(
                            email = tempEmail.email,
                            onBackClicked = { backStack.removeLastOrNull() }
                        )
                    }
                    entry<AuthScreen.EmailVerification> {
                        EmailVerificationScreen(
                            onBack = { backStack.removeLastOrNull() },
                            isLoading = verifyingPeriod.value,
                            isDone = isDone.value
                        )
                    }
                    entry<AuthScreen.EmailVerified> { data ->
                        EmailVerifiedScreen(
                            title = data.title,
                            toMainApp = {
                                backStack.clear()
                                goToMainApp()
                            },
                            message = data.message,
                            onBack = {
                                backStack.removeLastOrNull()
                            },
                            isDone = isDone.value
                        )
                    }
                }
            )
        }
    }
}
