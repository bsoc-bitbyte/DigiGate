package com.tpc.digigate.ui.navigation

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.tpc.digigate.ui.screens.authentication.emailVerificationEmailSentConfirmation.EmailConfirmationScreen
import com.tpc.digigate.ui.screens.authentication.emailVerified.EmailVerifiedScreen
import com.tpc.digigate.ui.screens.authentication.passwordResetEmailSentConfirmation.EmailSentConfirmationLayout
import com.tpc.digigate.ui.screens.authentication.forgetPassword.ForgetPasswordScreenLayout
import com.tpc.digigate.ui.screens.authentication.login.LoginScreenLayout
import com.tpc.digigate.ui.screens.authentication.register.RegisterScreenLayout
import com.tpc.digigate.ui.screens.onboarding.OnboardingPagerScreen

@Composable
fun AuthNavDisplay(
    goToMainApp: () -> Unit,
    context: Context,
) {
    val backStack = remember { mutableStateListOf<AuthScreen>(AuthScreen.OnBoarding) }

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
                        EmailConfirmationScreen(
                            onBack = { backStack.removeLastOrNull() },
                            toEmailVerified = {
                                backStack+= AuthScreen.EmailVerified
                            }
                        )
                    }
                    entry<AuthScreen.EmailVerified> { data ->
                        EmailVerifiedScreen(
                            toMainApp = {
                                backStack.clear()
                                goToMainApp()
                            },
                            onBack = {
                                backStack.removeLastOrNull()
                            },
                        )
                    }
                }
            )
        }
    }
}
