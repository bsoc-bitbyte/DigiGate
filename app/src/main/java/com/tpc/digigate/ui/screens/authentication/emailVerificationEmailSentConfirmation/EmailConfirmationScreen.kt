package com.tpc.digigate.ui.screens.authentication.emailVerificationEmailSentConfirmation

import android.content.Intent
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tpc.digigate.R

@Composable

fun EmailConfirmationScreen(
    viewModel: EmailConfirmationViewModel = hiltViewModel(),
    isLoading: Boolean = false,
    onBack: () -> Unit,
    isDone: Boolean = false
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsState().value

    if (uiState.message != null) {
        Toast.makeText(context, uiState.message, Toast.LENGTH_SHORT).show()
        viewModel.toastMessageShown()
    }
    Box(
        Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                TopBar(
                    modifier = Modifier.align(Alignment.TopCenter),
                    onBackClicked = { onBack() }
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = com.tpc.digigate.R.drawable.emailverification),
                    contentDescription = "email verification Page",
                    modifier = Modifier.aspectRatio(1.2f)
                )
                Text(
                    text = "Email Sent Successfully",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.padding(6.dp))
                Text(
                    text = "We’ve just sent an email to your inbox with a link to verify your email",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Thin,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.padding(10.dp))
                if (uiState.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Button(
                        onClick = {
                            viewModel.sendVerificationEmail()
                        },
                        enabled = uiState.canResend
                    ) {
                        Text(
                            text = if (uiState.canResend) "Resend Email" else "Resend in ${uiState.countdown}",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Button(onClick = { openGmailApp(context) }) {
                    Text(
                        text = "Open Gmail",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.titleMedium
                    )

                }
            }

        }
        if (isLoading) {
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
        if (isDone) {
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
                        Icon(
                            imageVector = Icons.Default.Done,
                            tint = Color.Green,
                            contentDescription = "Done",
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit
) {
    Box(
        modifier = modifier
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

private fun openGmailApp(context: android.content.Context) {
    try {
        val intent = Intent(Intent.ACTION_MAIN).apply {
            addCategory(Intent.CATEGORY_APP_EMAIL)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    } catch (_: Exception) {
        Toast.makeText(context, "No Email app found on this device.", Toast.LENGTH_SHORT).show()
    }
}
