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
fun CreateProfile1Screen() {
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
                    label = "Name",
                    value = "",
                    onValueChange = {},
                    placeholder = "",
                    errorMessage = "Please enter your name",
                )
                Spacer(Modifier.height(30.dp))

                AppTextField(
                    label = "Roll Number",
                    value = "",
                    onValueChange = {},
                    placeholder = "",
                    errorMessage = "Please enter your roll number",
                )
                Spacer(Modifier.height(30.dp))

                AppDropDown(
                    label = "Programme",
                    value = "",
                    onValueChange = {},
                    errorMessage = "Please select a valid programme.",
                    list = listOf("B.Tech", "M.Tech", "B.Des", "M.Des", "PhD"),
                )
                Spacer(Modifier.height(30.dp))

                AppDropDown(
                    label = "Branch",
                    value  = "",
                    onValueChange = {},
                    errorMessage = "Please enter a valid email id",
                    list = listOf("CSE", "ECE", "Design", "Mech", "SM"),
                )
                Spacer(Modifier.height(30.dp))

                AppDropDown(
                    label = "Year",
                    value = "",
                    onValueChange = {},
                    errorMessage = "Please select a valid year.",
                    list = listOf("1", "2", "3", "4", "5"),

                )
                Spacer(Modifier.height(25.dp))
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun CreateProfileScreen1Preview() {
    DigiGateTheme { CreateProfile1Screen() }
}