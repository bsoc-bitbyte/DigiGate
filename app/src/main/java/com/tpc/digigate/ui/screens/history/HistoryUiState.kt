package com.tpc.digigate.ui.screens.history

data class HistoryUiState(
    val historyItems: List<Month> = emptyList<Month>()
)

data class Month(
    val month:String = "",
    val monthData: List<HistoryMonthUiState> = emptyList<HistoryMonthUiState>()
)

data class HistoryMonthUiState(
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