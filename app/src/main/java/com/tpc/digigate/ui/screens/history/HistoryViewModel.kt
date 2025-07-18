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
            val year1 = Year(
                year = 2025,
                monthData = listOf(
                    Month(
                        month = 8,
                        dayEntries = listOf(
                            DayEntry(
                                day = 9,
                                outTime = "7:15 AM",
                                inTime = "6:30 PM",
                                isInVerified = true,
                                isOutVerified = true,
                            ),
                            DayEntry(
                                day = 25,
                                outTime = "11:35 AM",
                                inTime = "8:15 PM",
                                isInVerified = false,
                                isOutVerified = true,
                            ),
                        )
                    ),
                    Month(
                        month = 6,
                        dayEntries = listOf(
                            DayEntry(
                                day = 15,
                                outTime = "8:30 AM",
                                inTime = "10:40 PM",
                                isInVerified = true,
                                isOutVerified = true,
                            ),
                            DayEntry(
                                day = 13,
                                outTime = "10:30 AM",
                                inTime = "8:40 PM",
                                isInVerified = true,
                                isOutVerified = false,
                            ),
                            DayEntry(
                                day = 20,
                                outTime = "9:00 AM",
                                inTime = "7:30 PM",
                                isInVerified = false,
                                isOutVerified = false,
                            ),
                        )
                    ),
                )
            )
            val year2 = Year(
                year = 2024,
                monthData = listOf(
                    Month(
                        month = 1,
                        dayEntries = listOf(
                            DayEntry(
                                day = 9,
                                outTime = "7:15 AM",
                                inTime = "6:30 PM",
                                isInVerified = true,
                                isOutVerified = true,
                            ),
                            DayEntry(
                                day = 25,
                                outTime = "11:35 AM",
                                inTime = "8:15 PM",
                                isInVerified = false,
                                isOutVerified = true,
                            ),
                        )
                    ),
                    Month(
                        month = 10,
                        dayEntries = listOf(
                            DayEntry(
                                day = 15,
                                outTime = "8:30 AM",
                                inTime = "10:40 PM",
                                isInVerified = true,
                                isOutVerified = true,
                            ),
                            DayEntry(
                                day = 13,
                                outTime = "10:30 AM",
                                inTime = "8:40 PM",
                                isInVerified = true,
                                isOutVerified = false,
                            ),
                        )
                    )
                )
            )
            it.copy(yearData = listOf(year1,year2))
        }
    }
}