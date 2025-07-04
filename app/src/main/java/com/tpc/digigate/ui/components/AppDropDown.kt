package com.tpc.digigate.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpc.digigate.ui.theme.DigiGateTheme
import com.tpc.digigate.ui.theme.LightGray
import com.tpc.digigate.ui.theme.MidGray
import com.tpc.digigate.ui.theme.PrimaryText

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppDropDown(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    isError: Boolean = false,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    list: List<String>
) {
    val showError = isError && !errorMessage.isNullOrEmpty()

    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    var itemPosition by remember {
        mutableStateOf(-1)
    }
    val sheetState = rememberModalBottomSheetState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    onClick = {
                        showBottomSheet = true
                    }
                )
        ) {
            AppTextField(
                label = label,
                value = if (itemPosition>=0) list[itemPosition] else "",
                onValueChange = onValueChange,
                placeholder = "",
                errorMessage = errorMessage,
                enabled = false,
            )

            Icon(
                imageVector = Icons.Outlined.KeyboardArrowDown,
                contentDescription = "",
                tint = Color.DarkGray,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 10.dp)
            )

        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet  =  false
                },
                sheetState =  sheetState
            ) {
                list.forEachIndexed { index, item ->
                    val isSelected = (itemPosition == index)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            modifier = Modifier.fillMaxWidth(0.95f)
                                .height(60.dp),
                            onClick = {
                                onValueChange(item)
                                showBottomSheet = false
                                itemPosition = index
                            },
                            shape = RoundedCornerShape(5.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.background)
                            ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically

                            ) {
                                Text(
                                    text = item,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                RadioButton(
                                    selected = isSelected,
                                    onClick = null
                                )
                            }

                        }
                    }
                    Spacer(Modifier.height(5.dp))

                }
            }
        }
        if (showError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AppDropDownPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        DigiGateTheme {
            AppDropDown(
                label = "Email",
                value = "",
                onValueChange = {},
                errorMessage = "Please enter a valid email id",
                list = listOf("abc", "def")
            )
        }
    }
}