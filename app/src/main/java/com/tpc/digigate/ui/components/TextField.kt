package com.tpc.digigate.ui.components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction
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
){


    var email by rememberSaveable {
        mutableStateOf("")
    }
    Box(modifier = Modifier, contentAlignment = Alignment.Center){

        OutlinedTextField(modifier = Modifier.fillMaxWidth().padding(16.dp),shape = RoundedCornerShape(9.dp)
            ,value = email, onValueChange = {email = it}, label = {
                Text(text="Email")

            },
                    singleLine = true
        )


    }



}




@Composable
fun AppPasswordField(
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
isPasswordVisible: Boolean = false,
onTogglePasswordVisibility: (() -> Unit)? = null,
leadingIcon: (@Composable (() -> Unit))? = null,
trailingIcon: (@Composable (() -> Unit))? = null
) {
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            shape = RoundedCornerShape(9.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                val icon = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                val description = if (isPasswordVisible) "Hide password" else "Show password"
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(imageVector = icon, contentDescription = description)
                }
            },
            singleLine = true
        )

}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        AppTextField(label = "Email", value = "", onValueChange = {})
        AppPasswordField(label = "Password", value = "", onValueChange = {})

    }

}


