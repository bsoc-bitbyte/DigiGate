package com.tpc.digigate.ui.components

import android.content.res.Configuration
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpc.digigate.ui.theme.PrimaryText
import com.tpc.digigate.ui.theme.PureWhite

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    errorMessage: String? = null,
    isError: Boolean = false,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null,
    enabled: Boolean = true
) {
    val showError = isError && !errorMessage.isNullOrEmpty()

    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = value,
            textStyle = MaterialTheme.typography.bodyLargeEmphasized,
            onValueChange = onValueChange,
            isError = showError,
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .background(PureWhite, RoundedCornerShape(9.dp))
                .border(
                    width = 1.dp,
                    color = if (showError) Color.Transparent else Color.Transparent,
                    shape = RoundedCornerShape(9.dp)
                )
                .padding(vertical = 2.dp),
            shape = RoundedCornerShape(9.dp),
            label = { Text(text = label, style = MaterialTheme.typography.bodyMedium) },
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            },
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

                disabledContainerColor = PureWhite,
                disabledIndicatorColor = PureWhite,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,

                focusedLabelColor = if (showError) Color.Red else MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = if (showError) Color.Red else Color.Gray,
                errorLabelColor = Color.Red,
                disabledLabelColor = Color.Gray,

                focusedTextColor = PrimaryText,
                unfocusedTextColor = PrimaryText,
                errorTextColor = PrimaryText,
                disabledTextColor = PrimaryText,

                focusedPlaceholderColor = Color.Gray,
                unfocusedPlaceholderColor = Color.Gray,
                errorPlaceholderColor = Color.Gray,

                cursorColor = MaterialTheme.colorScheme.primary,
                errorCursorColor = MaterialTheme.colorScheme.primary,

                errorTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )

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

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
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