package com.tpc.digigate.referenceCodes.ref2

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor() : ViewModel() {

    private val _attendanceUIState = MutableStateFlow(AttendanceUIState())
    val attendanceUIState: StateFlow<AttendanceUIState> = _attendanceUIState.asStateFlow()

    init {
        _attendanceUIState.update {
            val d1 = Date(
                date = "16 June 2025",
                students = listOf(
                    Student(name = "Mohit", rollNo = "23bcs001"),
                    Student(name = "Mehul", rollNo = "23bcs002")
                )
            )
            val d2 = Date(
                date = "17 June 2025",
                students = listOf(
                    Student(name = "Mohit", rollNo = "23bcs001"),
                    Student(name = "Mehul", rollNo = "23bcs002")
                )
            )
            it.copy(dates = listOf(d2, d1))
        }
    }
}