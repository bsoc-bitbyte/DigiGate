package com.tpc.digigate.ui.screens.createProfile

import android.app.VoiceInteractor
import android.content.res.Configuration
import android.media.Image
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tpc.digigate.R
import com.tpc.digigate.ui.theme.DigiGateTheme

@Composable
fun CreateProfile3Screen() {
    var imageUri1 by remember { mutableStateOf<Uri?>(null) }
    var imageUri2 by remember { mutableStateOf<Uri?>(null) }

    val pickImage1Launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUri1 = uri
        }
    )

    val pickImage2Launcher  =  rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            imageUri2 = uri
        }
    )

    Column(
        modifier = Modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text (
            text = "Personal Information",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = "You cannot change this later",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(45.dp))
        Card(
            modifier = Modifier.fillMaxWidth(0.86f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {

            if (imageUri1==null){
                Image(
                    painter = painterResource(R.drawable.frame1),
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable(
                            onClick = {
                                pickImage1Launcher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                        ),
                    contentScale = ContentScale.FillWidth
                )
            }
            else {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(12.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUri1)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Image1",
                        contentScale = ContentScale.FillWidth,
                    )
                    IconButton(
                        onClick = {
                            imageUri1 = null
                        },
                        Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }

        }
        Spacer(Modifier.height(10.dp))
        Card(
            modifier = Modifier.fillMaxWidth(0.86f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {

            if (imageUri2==null){
                Image(
                    painter = painterResource(R.drawable.frame2),
                    contentDescription = "image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                        .clickable(
                            onClick = {
                                pickImage2Launcher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            }
                        ),
                    contentScale = ContentScale.FillWidth
                )
            }
            else {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .padding(12.dp)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUri2)
                            .crossfade(true)
                            .build(),
                        contentDescription = "Image2",
                        contentScale = ContentScale.FillWidth
                    )
                    IconButton(
                        onClick = {
                            imageUri2 = null
                        },
                        Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.close),
                            contentDescription = "",
                            modifier = Modifier.size(25.dp)
                        )
                    }

                }
            }

        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CreateProfileScreen3Preview() {
    DigiGateTheme { CreateProfile3Screen() }
}