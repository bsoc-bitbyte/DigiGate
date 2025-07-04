package com.tpc.digigate.ui.screens.createProfile

import android.content.res.Configuration
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpc.digigate.ui.theme.DigiGateTheme
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants

@Composable
fun CreateProfilePager() {
    val pagerState = rememberPagerState( pageCount = { 3 })
    val coroutineScope = rememberCoroutineScope()
    HorizontalPager(pagerState) { page->
        Column (
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Create Profile",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )
            when (page) {
                0 -> {
                    CreateProfile1Screen()
                }
                1 -> {
                    CreateProfile2Screen()
                }
                else -> {
                    CreateProfile3Screen()
                }
            }
            if (page==2) {
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Save",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
            }
            else{
                Button(
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = pagerState.currentPage + 1
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                ) {
                    Text(
                        text = "Next",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier.height(16.dp)

            ) {
                repeat(pagerState.pageCount) { page ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .size(if (pagerState.currentPage == page) 10.dp else 8.dp)
                            .background(
                                color = if (pagerState.currentPage == page) MaterialTheme.colorScheme.primary else Color.LightGray,
                                shape = RoundedCornerShape(percent = 50)
                            )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CreateProfilePagerPreview() {
    DigiGateTheme {
        CreateProfilePager()
    }
}