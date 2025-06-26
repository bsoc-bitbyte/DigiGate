package com.tpc.digigate.ui.screens.authentication.register

import android.content.Context
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.tpc.digigate.ui.theme.DigiGateTheme
import com.tpc.digigate.R
import com.tpc.digigate.ui.components.AppPasswordField
import com.tpc.digigate.ui.components.AppTextField
import com.tpc.digigate.ui.theme.PureWhite

@Composable
fun RegisterScreenLayout(
    goToMainApp: () -> Unit,
    onHaveAccount: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel(),
    context: Context? = null,
    goToEmailVerificationScreen: () -> Unit
) {
    val uiState by viewModel.registerUiState.collectAsState()

    var isPasswordVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    if (uiState.toastMessage != null && uiState.toastMessage?.isNotBlank() == true) {
        Toast.makeText(context, uiState.toastMessage, Toast.LENGTH_SHORT).show()
        viewModel.toastMessageShown()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.image_5),
            contentDescription = stringResource(R.string.register_image),
            modifier = Modifier.aspectRatio(1.3f)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.primary,
            shape = RoundedCornerShape(30.dp)
        ) {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.register),
                    color = Color.Black,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(top = 35.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))
                AppTextField(
                    label = "Email",
                    placeholder = "Enter your Email Id",
                    value = uiState.email,
                    onValueChange = {
                        viewModel.emailInput(it)
                    },
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    onImeAction = { focusManager.moveFocus(focusDirection = FocusDirection.Down) },
                    errorMessage = "Please enter a valid email id",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(14.dp))
                AppPasswordField(
                    label = "Password",
                    placeholder = "Enter your Password",
                    value = uiState.password,
                    onValueChange = {
                        viewModel.passwordInput(it)
                    },
                    errorMessage = if (uiState.password.length < 6) "Password too short (min 6 chars)" else null,
                    isPasswordVisible = isPasswordVisible,
                    onTogglePasswordVisibility = { isPasswordVisible = !isPasswordVisible },
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                    onImeAction = { focusManager.clearFocus() },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
                Spacer(modifier = Modifier.height(35.dp))
                Button(
                    onClick = { viewModel.onClickRegister(goToEmailVerificationScreen = goToEmailVerificationScreen) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(55.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    enabled = viewModel.isEnabled()
                ) {
                    Text(
                        text = stringResource(R.string.register),
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = {
                        viewModel.googleSignIn(
                            context!!,
                            goToMainApp,
                            goToEmailVerificationScreen = goToEmailVerificationScreen
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(55.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PureWhite
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.google_icon),
                            contentDescription = stringResource(R.string.google),
                            tint = Color.Gray,
                            modifier = Modifier.aspectRatio(0.6f)
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                        Text(
                            text = "Continue With Google",
                            color = Color.DarkGray,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Row {
            Text(
                text = "Already have an account?",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            Text(
                text = stringResource(R.string.login),
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color(0xFFE57C28),
                modifier = Modifier.clickable(
                    onClick = onHaveAccount
                )
            )

        }
    }
    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White.copy(alpha = 0.95f),
                shadowElevation = 8.dp,
                modifier = Modifier.size(100.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = colorResource(R.color.SageDark),
                        strokeWidth = 6.dp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    DigiGateTheme {
        val viewModel = viewModel<RegisterViewModel>()
        RegisterScreenLayout(
            goToMainApp = {},
            onHaveAccount = {},
            viewModel = viewModel,
            goToEmailVerificationScreen = {}
        )
    }
}