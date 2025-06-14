package com.tpc.digigate.ui.screens.onboarding
import androidx.annotation.DrawableRes
import com.tpc.digigate.R

sealed class OnboardingModel(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    object FirstPage : OnboardingModel(
        image = R.drawable.page1,
        title = "Skip the logbook.\nJust tap & go.",
        description = "Generate your digital QR pass in seconds. No slips. No delays. Your ID, verified instantly at any campus gate."
    )

    object SecondPage : OnboardingModel(
        image = R.drawable.page2,
        title = "Your logbook,\nin your pocket.",
        description = "All your check-ins saved instantly. View your movement history anytime."
    )

    object ThirdPage : OnboardingModel(
        image = R.drawable.page3,
        title = "Scanned. Verified.\nDone.",
        description = "Guards verify instantly. Admins get real-time data. You just walk in — no questions, no queues."
    )
}

