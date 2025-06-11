package com.tpc.digigate.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tpc.digigate.ui.theme.PureWhite

@Composable
fun AppTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    errorMessage: String? = null,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null
) {
    val isError = !errorMessage.isNullOrEmpty()

    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            isError = isError,
            modifier = Modifier
                .fillMaxWidth()
                .background(PureWhite, RoundedCornerShape(9.dp))
                .border(
                    width = 0.6.dp,
                    color = if (isError) Color.Red else Color.Transparent,
                    shape = RoundedCornerShape(9.dp)
                ),
            shape = RoundedCornerShape(9.dp),
            label = { Text(text = label) },
            placeholder = { Text(text = placeholder, modifier = Modifier, Color.LightGray) },
            singleLine = isSingleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onAny = { onImeAction() }
            ),
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PureWhite,
                unfocusedContainerColor = PureWhite,
                errorContainerColor = PureWhite,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Red,

                focusedLabelColor = if (isError) Color.Red else MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = if (isError) Color.Red else Color.Gray,
                errorLabelColor = Color.Red,

                focusedTextColor = Color.LightGray,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                errorTextColor = MaterialTheme.colorScheme.onSurface,

                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
                errorPlaceholderColor = Color.Red,

                cursorColor = MaterialTheme.colorScheme.primary,
                errorCursorColor = Color.Red
            )

        )

        if (isError) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(8.dp), fontSize = 15.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppTextFieldPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        MaterialTheme {
            AppTextField(
                label = "Email",
                value = "",
                onValueChange = {},
                placeholder = "",
                errorMessage = "Please enter a valid email id"
            )
        }
    }
}