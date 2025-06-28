package com.tpc.digigate.ui.screens.authentication.emailSentConfirmation

import android.content.Intent
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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tpc.digigate.ui.theme.DigiGateTheme
import com.tpc.digigate.R

@Composable
fun EmailSentConfirmationLayout(
    email: String,
    onBackClicked: () -> Unit,
    viewModel: EmailConfirmationViewModel = hiltViewModel()
) {
    val uiState by viewModel.emailConfirmationUiState.collectAsState()
    val context = LocalContext.current
    LaunchedEffect(email) {
        viewModel.resetState()
        viewModel.setEmail(email)
    }
    LaunchedEffect(uiState.toastMessage) {
        if (uiState.toastMessage != null) {
            Toast.makeText(context, uiState.toastMessage, Toast.LENGTH_SHORT).show()
        }
        viewModel.clearToast()
    }
    EmailSentConfirmationContent(
        uiState = uiState,
        onResendClicked = viewModel::onResendClicked,
        onBack = onBackClicked
    )
}

@Composable
fun EmailSentConfirmationContent(
    uiState: EmailConfirmationUiState,
    onBack: () -> Unit,
    onResendClicked: () -> Unit,
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(
            onBackClicked =  { onBack() }
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.mail_sent_rafiki_1),
                    contentDescription = stringResource(R.string.email_confirmation),
                    modifier = Modifier.aspectRatio(1.3f)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Email sent successfully",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 26.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "We’ve just sent an email to your inbox with a link to reset your password",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.W500
                        ),
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                if (!uiState.canResend) {
                    Text(
                        text = "Resend Mail in ${uiState.countdown}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                } else {
                    Button(
                        onClick = { onResendClicked() },
                    ) {
                        Text(
                            text = stringResource(R.string.resend),
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        try {
                            val intent = Intent(Intent.ACTION_MAIN).apply {
                                addCategory(Intent.CATEGORY_APP_EMAIL)
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(
                                context,
                                "No Email app found on this device.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text(
                        text = "Check Inbox",
                        color = Color.Black,
                        style = MaterialTheme.typography.titleMedium
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
        IconButton(
            onClick = onBackClicked
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Navigate back",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EmailSentConfirmationPreview() {
    DigiGateTheme {
        EmailSentConfirmationContent(
            uiState = EmailConfirmationUiState(),
            onResendClicked = {},
            onBack = {}
        )
    }
}