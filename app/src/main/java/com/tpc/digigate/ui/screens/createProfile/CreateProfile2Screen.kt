package com.tpc.digigate.ui.screens.createProfile

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tpc.digigate.ui.components.AppDropDown
import com.tpc.digigate.ui.components.AppTextField
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.tpc.digigate.ui.theme.DigiGateTheme


@Composable
fun CreateProfile2Screen() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Personal Information",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(35.dp))

        Card(
            modifier = Modifier.fillMaxWidth(0.86f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(Modifier.height(25.dp))
                AppTextField(
                    label = "Phone Number",
                    value = "",
                    onValueChange = {},
                    placeholder = "",
                    errorMessage = "Please enter your phone number",
                )
                Spacer(Modifier.height(30.dp))

                AppTextField(
                    label = "Parent's Phone Number",
                    value = "",
                    onValueChange = {},
                    placeholder = "",
                    errorMessage = "Please enter your parent's phone number",
                )
                Spacer(Modifier.height(30.dp))

                AppDropDown(
                    label = "Hostel",
                    value = "",
                    onValueChange = {},
                    list = listOf("H1", "H4", "H3", "Panini", "Maa Saraswati", "Nagarjuna"),
                )
                Spacer(Modifier.height(30.dp))

                AppTextField(
                    label = "Room Number",
                    value = "",
                    onValueChange = {},
                    placeholder = "",
                    errorMessage = "Please enter your name",
                )
                Spacer(Modifier.height(30.dp))

                AppTextField(
                    label = "Permanent Address",
                    value = "",
                    onValueChange = {},
                    placeholder = "",
                    errorMessage = "Please enter your permanent address",
                )
                Spacer(Modifier.height(25.dp))
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun CreateProfileScreen2Preview() {
    DigiGateTheme {
        CreateProfile2Screen()
    }
}