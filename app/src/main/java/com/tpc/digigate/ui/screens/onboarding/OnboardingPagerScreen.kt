package com.tpc.digigate.ui.screens.onboarding
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpc.digigate.ui.theme.BackgroundDefault
import kotlinx.coroutines.launch



@Composable
fun OnboardingPagerScreen(onFinished: () -> Unit) {
    val pages = listOf(
        OnboardingModel.FirstPage,
        OnboardingModel.SecondPage,
        OnboardingModel.ThirdPage
    )

    val pagerState = rememberPagerState(initialPage = 0) { pages.size }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDefault),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            state = pagerState
        ) { page ->
            OnboardingPageContent(pages[page])
        }

        Spacer(modifier = Modifier.height(24.dp))

        IndicatorUi(
            pageSize = pages.size,
            currentPage = pagerState.currentPage
        )

        Spacer(modifier = Modifier.height(24.dp))

        ButtonUi(
            text = if (pagerState.currentPage == pages.lastIndex) "Get Started" else "Next"
        ) {
            if (pagerState.currentPage == pages.lastIndex) {
                onFinished()
            } else {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    name = "Dark Mode"
)
@Composable
fun PreviewOnboarding() {
    OnboardingPagerScreen(onFinished = {})
}
