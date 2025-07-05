package com.tpc.digigate.ui.screens.history

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tpc.digigate.R
import com.tpc.digigate.ui.theme.DigiGateTheme


@Composable
fun HistoryScreen(viewModel: HistoryViewModel = hiltViewModel()) {
    val uiState = viewModel.historyUiState.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.history),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 30.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var firstMonth = false
            item {
                uiState.yearData
                    .sortedByDescending { it.year }
                    .forEach { year ->
                        year.monthData
                            .sortedByDescending { it.month }
                            .forEach { month ->
                                Box(
                                    modifier = Modifier.fillMaxSize(0.8f),
                                ) {
                                    Text(
                                        text = MonthOrder[month.month].toString(),
                                        style = MaterialTheme.typography.headlineMedium.copy(
                                            fontWeight = FontWeight.Bold
                                        ),
                                        color = MaterialTheme.colorScheme.onBackground,
                                        modifier = Modifier
                                    )
                                    if (!firstMonth) {
                                        Icon(
                                            imageVector = Icons.Outlined.CalendarToday,
                                            contentDescription = stringResource(R.string.calender_today),
                                            tint = MaterialTheme.colorScheme.onBackground,
                                            modifier = Modifier
                                                .align(Alignment.CenterEnd)
                                                .clickable(onClick = {})
                                        )
                                        firstMonth = true
                                    }
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                month.dayEntries
                                    .sortedWith(compareBy { it.day })
                                    .forEach { data ->
                                        EntryCard(
                                            year = year.year,
                                            month = month.month,
                                            info = data
                                        )
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }
                            }
                    }
            }
        }

    }
}

@Composable
fun EntryCard(
    year: Int,
    month: Int,
    info: DayEntry,
) {
    val cardMonth = MonthOrder[month].toString().take(3).uppercase()
    val cardYear = year.toString().takeLast(2)
    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .border(4.dp, MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(25.dp)),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.padding(start = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = info.day.toString(),
                    color = Color.Black,
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.displayMedium.fontSize * 1.2f
                    ),
                )
                Text(
                    text = "${cardMonth.uppercase()} '${cardYear}",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.offset(y = (-8).dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                LineAndDotImage()
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Column(
                    verticalArrangement = Arrangement.Center,
                ) {
                    TimeData(
                        label = stringResource(R.string.out_time),
                        time = info.outTime,
                        isVerified = info.isOutVerified,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    TimeData(
                        label = stringResource(R.string.in_time),
                        time = info.inTime,
                        isVerified = info.isInVerified
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun TimeData(
    label: String,
    time: String,
    isVerified: Boolean
) {
    val (timeValue, period) = time.split(" ")
    Column {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize * 1.2f,
                        fontWeight = FontWeight.ExtraBold
                    )
                ) {
                    append("${label}: $timeValue")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Black,
                        fontSize = MaterialTheme.typography.labelSmall.fontSize,
                        fontWeight = FontWeight.ExtraBold,
                        baselineShift = BaselineShift.Superscript
                    )
                ) {
                    append(period)
                }
            },
        )

        Text(
            text = if (isVerified) stringResource(R.string.verified) else stringResource(R.string.not_verified),
            color = Color.Black,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier.offset(y = (-3).dp)
        )
    }
}


@Composable
fun LineAndDotImage() {

    Column(
        modifier = Modifier.offset(y = (-4).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
                .size(12.dp),
        )
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .width(2.dp)
                .height(37.dp),
        )
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
                .size(12.dp)
        )
    }
}

@Preview
@Composable
fun EntryCardPreview() {
    DigiGateTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp),
            contentAlignment = Alignment.Center
        ) {
            EntryCard(
                year = 2025,
                month = 6,
                info = DayEntry(
                    day = 9,
                    outTime = "7:15 AM",
                    inTime = "6:30 PM",
                    isInVerified = true,
                    isOutVerified = true,
                ),
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HistoryScreenPreview() {
    DigiGateTheme {
        HistoryScreen()
    }
}

