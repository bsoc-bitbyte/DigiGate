package com.tpc.digigate.ui.screens.history

import android.content.res.Configuration
import com.tpc.digigate.R
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.tpc.digigate.ui.theme.DigiGateTheme


data class HistoryCardUiState(
    val month: String,
    val date: String,
    val OutTimeHour: Int,
    val OutTimeMinute: Int,
    val OutTimePeriod: String,
    val InTimeHour: Int,
    val InTimeMinute: Int,
    val InTimePeriod: String,
    val isVerified: Boolean,
    val year: Int,
)

val MonthOrder = listOf(
    "DECEMBER",
    "NOVEMBER",
    "OCTOBER",
    "SEPTEMBER",
    "AUGUST",
    "JULY",
    "JUNE",
    "MAY",
    "APRIL",
    "MARCH",
    "FEBRUARY",
    "JANUARY"
)

@Composable
fun HistoryScreen(modifier: Modifier = Modifier) {
    val HistoryItems = remember {
        listOf(
            HistoryCardUiState(
                month = "June",
                year = 25,
                date = "15",
                OutTimeHour = 8,
                OutTimeMinute = 30,
                OutTimePeriod = "AM",
                InTimeHour = 10,
                InTimeMinute = 40,
                InTimePeriod = "PM",
                isVerified = true,
            ),
            HistoryCardUiState(
                month = "June",
                year = 25,
                date = "13",
                OutTimeHour = 10,
                OutTimeMinute = 30,
                OutTimePeriod = "AM",
                InTimeHour = 8,
                InTimeMinute = 40,
                InTimePeriod = "PM",
                isVerified = false,
            ),
            HistoryCardUiState(
                month = "May",
                year = 25,
                date = "09",
                OutTimeHour = 7,
                OutTimeMinute = 15,
                OutTimePeriod = "AM",
                InTimeHour = 6,
                InTimeMinute = 30,
                InTimePeriod = "PM",
                isVerified = true,
            ),
            HistoryCardUiState(
                month = "May",
                year = 25,
                date = "25",
                OutTimeHour = 11,
                OutTimeMinute = 35,
                OutTimePeriod = "AM",
                InTimeHour = 8,
                InTimeMinute = 15,
                InTimePeriod = "PM",
                isVerified = false,
            )
        )
    }

    val MonthData = HistoryItems
        .sortedWith(compareBy({ MonthOrder.indexOf(it.month.uppercase()) }, { it.date.toInt() }))
        .groupBy { it.month }
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
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MonthData.entries.forEachIndexed { index, (month, data) ->
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(0.9f),
                    ) {
                        Text(
                            text = month,
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.padding(start = 12.dp)
                        )
                        if (index == 0) {
                            Icon(
                                imageVector = Icons.Outlined.CalendarToday,
                                contentDescription = stringResource(R.string.calender_today),
                                tint = MaterialTheme.colorScheme.onBackground,
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(end = 10.dp)
                                    .clickable(onClick = {})
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
                items(data) { item ->
                    EntryCard(info = item)
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

@Composable
fun EntryCard(
    info: HistoryCardUiState,
) {
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
                    text = info.date,
                    color = Color.Black,
                    style = MaterialTheme.typography.displayMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = MaterialTheme.typography.displayMedium.fontSize * 1.2f
                    ),
                )
                Text(
                    text = "${info.month.toString().uppercase()} '${info.year}",
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.offset(y = -8.dp)
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
                        hour = info.OutTimeHour,
                        minute = info.OutTimeMinute,
                        period = info.OutTimePeriod,
                        isVerified = info.isVerified,
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    TimeData(
                        label = stringResource(R.string.in_time),
                        hour = info.InTimeHour,
                        minute = info.InTimeMinute,
                        period = info.InTimePeriod,
                        isVerified = info.isVerified
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
    hour: Int,
    minute: Int,
    period: String,
    isVerified: Boolean
) {
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
                    append("${label}: ${hour}:${minute}")
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
            modifier = Modifier.offset(y = -3.dp)
        )
    }
}


@Composable
fun LineAndDotImage() {

    Column(
        modifier = Modifier.offset(y = -4.dp),
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
            modifier = Modifier.fillMaxWidth().height(140.dp)
        ) {
            EntryCard(
                HistoryCardUiState(
                    month = "MAY",
                    year = 25,
                    date = "09",
                    OutTimeHour = 7,
                    OutTimeMinute = 15,
                    OutTimePeriod = "AM",
                    InTimeHour = 6,
                    InTimeMinute = 30,
                    InTimePeriod = "PM",
                    isVerified = true,
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

