package com.tpc.digigate.referenceCodes.ref1

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import com.tpc.digigate.referenceCodes.ref1.FormViewModel

@Composable
fun Form(viewModel: FormViewModel = hiltViewModel()) {
    val uiState by viewModel.formUiState.collectAsState()

    Spacer(modifier = Modifier.height(20.dp))
    Row {
        Text("Name")
        Spacer(modifier = Modifier.width(12.dp))
        TextField(value = uiState.name, onValueChange = { viewModel.nameInput(it) })
    }
    Spacer(modifier = Modifier.height(20.dp))
    Button(onClick = { viewModel.onSubmitClicked() }) {
        Text("Submit")
    }

}