package com.tpc.digigate.referenceCodes.ref2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AttendanceScreen(viewModel: AttendanceViewModel = hiltViewModel()) {
    val uiState = viewModel.attendanceUIState.collectAsState().value

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            uiState.dates.forEach {
                Text(it.date)
                Column(modifier = Modifier.padding(20.dp)) {
                    it.students.forEach { student ->
                        Row {
                            Text(student.name)
                            Spacer(modifier = Modifier.width(20.dp))
                            Text(student.rollNo)
                        }
                    }
                }
            }
        }
    }
}