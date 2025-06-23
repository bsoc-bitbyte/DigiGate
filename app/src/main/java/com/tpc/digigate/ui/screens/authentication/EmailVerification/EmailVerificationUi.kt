package com.tpc.digigate.ui.screens.authentication.EmailVerification

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tpc.digigate.data.firebase.auth.FirebaseServices
import com.tpc.digigate.domain.model.AuthResult
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@Composable
fun EmailVerificationScreen(viewModel: EmailVerificationViewModel, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var canResend by remember { mutableStateOf(false) }
    var countdown by remember { mutableStateOf(30) }
    var trigger by remember { mutableStateOf(0) }

    LaunchedEffect(trigger) {
        canResend = false
        countdown = 30
        while (countdown > 0) {
            delay(1000)
            countdown--
        }
        canResend = true
    }

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = com.tpc.digigate.R.drawable.emailverification),
                contentDescription = "",
                modifier = Modifier.size(300.dp)
            )
            Text(
                text = "Email Sent Successfully",
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "We’ve just sent an email to your inbox with a link to reset your password",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Thin,
                color = Color.Gray
            )

            if (viewModel.isloading) {
                CircularProgressIndicator()
            } else {
                Button(
                    modifier = Modifier.clip(RoundedCornerShape(24.dp)),
                    onClick = {
                        viewModel.sendVerificationEmail {
                            when (it) {
                                is AuthResult.Success -> {
                                    Toast.makeText(
                                        context,
                                        "Email Sent Successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                is AuthResult.Error -> {
                                    Toast.makeText(
                                        context,
                                        "Error: ${it.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }

                                else -> Unit
                            }
                        }

                        trigger++
                    },
                    enabled = canResend
                ) {
                    Text(text = if (canResend) "Resend Email" else "Resend in $countdown")
                }
            }

            Button(onClick = {
                openGmailApp(context)
            }) {
                Text(text = "Open Gmail")
            }
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
    } catch (e: Exception) {
        Toast.makeText(context, "No Email app found on this device.", Toast.LENGTH_SHORT).show()
    }
}


@SuppressLint("ViewModelConstructorInComposable")
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun EmailVerificationpreview() {
    val fakeviewModel = EmailVerificationViewModel(object : FirebaseServices() {})
    EmailVerificationScreen(viewModel = fakeviewModel)
}