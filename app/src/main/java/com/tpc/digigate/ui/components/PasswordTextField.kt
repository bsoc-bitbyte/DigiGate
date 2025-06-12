package com.tpc.digigate.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpc.digigate.ui.theme.PureWhite
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun AppPasswordField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String = "",
    errorMessage: String? = null,
    isError: Boolean = false,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    isPasswordVisible: Boolean = false,
    onTogglePasswordVisibility: (() -> Unit)? = null,
    leadingIcon: (@Composable (() -> Unit))? = null,
    trailingIcon: (@Composable (() -> Unit))? = null
) {
    val showError = isError && !errorMessage.isNullOrEmpty()

    val finalTrailingIcon: @Composable (() -> Unit)? = if (onTogglePasswordVisibility != null) {
        {
            val image = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
            val description = if (isPasswordVisible) "Hide password" else "Show password"
            IconButton(onClick = onTogglePasswordVisibility) {
                Icon(imageVector = image, contentDescription = description)
            }
        }
    } else {
        trailingIcon
    }

    Column(modifier = modifier.fillMaxWidth()) {
        TextField(
            value = value,
            textStyle = MaterialTheme.typography.bodyLargeEmphasized,
            onValueChange = onValueChange,
            isError = showError,
            modifier = Modifier
                .fillMaxWidth()
                .background(PureWhite, RoundedCornerShape(12.dp))
                .border(
                    width = 1.dp,
                    color = if (showError) Color.Transparent else Color.Transparent,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 2.dp),
            shape = RoundedCornerShape(12.dp),
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
            visualTransformation = if (isPasswordVisible)
                VisualTransformation.None
            else
                PasswordVisualTransformation(mask = '*'),
            leadingIcon = leadingIcon,
            trailingIcon = finalTrailingIcon,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = PureWhite,
                unfocusedContainerColor = PureWhite,
                errorContainerColor = PureWhite,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,

                focusedLabelColor = if (showError) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedLabelColor = if (showError) Color.Red else MaterialTheme.colorScheme.onSurfaceVariant,
                errorLabelColor = Color.Red,

                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                errorTextColor = MaterialTheme.colorScheme.onSurface,

                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                errorPlaceholderColor = Color.Gray,

                cursorColor = MaterialTheme.colorScheme.primary,
                errorCursorColor = MaterialTheme.colorScheme.primary,

                focusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppPasswordFieldPreview() {

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            var passwordValue by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }

            AppPasswordField(
                label = "Password",
                value = passwordValue,
                onValueChange = { passwordValue = it },
                placeholder = "Enter your password",
                errorMessage = if (passwordValue.length < 8) "Password too short (min 8 chars)" else null,
                isPasswordVisible = passwordVisible,
                onTogglePasswordVisibility = { passwordVisible = !passwordVisible }
            )
        }
    }
}