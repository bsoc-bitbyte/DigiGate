package com.tpc.digigate.ui.screens.history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class HistoryViewModel @Inject constructor() : ViewModel() {
    private val _historyUiState = MutableStateFlow(HistoryUiState())
    val historyUiState: StateFlow<HistoryUiState> = _historyUiState.asStateFlow()

    init {
        _historyUiState.update {
            val month1 = Month(
                month = "June",
                monthData = listOf(
                    HistoryMonthUiState(
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
                    HistoryMonthUiState(
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
                )
            )
            val month2 = Month(
                month = "May",
                monthData = listOf(
                    HistoryMonthUiState(
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
                    HistoryMonthUiState(
                        year = 25,
                        date = "25",
                        OutTimeHour = 11,
                        OutTimeMinute = 35,
                        OutTimePeriod = "AM",
                        InTimeHour = 8,
                        InTimeMinute = 15,
                        InTimePeriod = "PM",
                        isVerified = false,
                    ),
                )
            )
            it.copy(historyItems = listOf(month1,month2))
        }
    }
}