package com.tpc.digigate.ui.screens.authentication.forgetPassword

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tpc.digigate.ui.theme.DigiGateTheme
import com.tpc.digigate.R
import com.tpc.digigate.ui.components.AppTextField
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource

@Composable
fun ForgetPasswordScreenLayout(
    onBackClicked: () -> Unit,
    onResetLinkSendClicked: (String) -> Unit,
    viewModel: ForgetPasswordViewModel = hiltViewModel()
) {
    val uiState by viewModel.forgetPasswordUiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.resetState()
    }
    LaunchedEffect(uiState.toastMessage) {
        if (uiState.toastMessage != null) {
            Toast.makeText(context, uiState.toastMessage, Toast.LENGTH_SHORT).show()
        }
        viewModel.clearToast()
    }
    ForgetPasswordScreenContent(
        uiState = uiState,
        onEmailInput = viewModel::emailInput,
        onBack = onBackClicked,
        onSendClicked = { viewModel.onSendClicked(onResetLinkSendClicked) }
    )
}

@Composable
fun ForgetPasswordScreenContent(
    uiState: ForgetScreenUiState,
    onBack: () -> Unit,
    onEmailInput: (String) -> Unit,
    onSendClicked: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            TopBar(
                onBackClicked = { onBack() }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding()),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.forgot_password_pana_1),
                    contentDescription = stringResource(R.string.forget_password),
                    modifier = Modifier.aspectRatio(1.3f)
                )
                Spacer(modifier = Modifier.padding(40.dp))
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(30.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(bottom = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Spacer(modifier = Modifier.padding(20.dp))
                        Text(
                            text = "Enter your registered email",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.padding(24.dp))
                        AppTextField(
                            label = stringResource(R.string.enter_email),
                            value = uiState.email,
                            onValueChange = { onEmailInput(it) },
                            placeholder = "23xyz@iiitdmj.ac.in",
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Done,
                            isError = uiState.isEmailError,
                            errorMessage = "Please enter a valid email id",
                            onImeAction = { focusManager.clearFocus() },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = stringResource(R.string.email)
                                )
                            },
                            enabled = true,
                            modifier = Modifier.padding(horizontal = 26.dp)
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Button(
                            onClick = {
                                onSendClicked()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(9.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp)
                                .padding(horizontal = 26.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.send_reset_link),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                ),
                            )
                        }
                        Spacer(modifier = Modifier.padding(16.dp))
                    }
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
    }
}


@Composable
fun TopBar(
    onBackClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(horizontal = 16.dp),
    ) {
        Text(
            text = stringResource(R.string.forget_password),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(
            onClick = onBackClicked
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Navigate back",
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ForgetScreenPreview() {
    DigiGateTheme {
        ForgetPasswordScreenContent(
            uiState = ForgetScreenUiState(
                email = ""
            ),
            onSendClicked = {},
            onEmailInput = {},
            onBack = {}
        )
    }
}