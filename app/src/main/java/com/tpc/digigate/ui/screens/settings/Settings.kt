package com.tpc.digigate.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.tpc.digigate.R
import com.tpc.digigate.domain.model.SupportedThemes
import com.tpc.digigate.ui.theme.DigiGateTheme

@Composable
fun Settings(viewModel: SettingsViewModel = hiltViewModel(),
             onBackClick: () -> Unit
) {
    val currentTheme = viewModel.currentTheme.collectAsState().value
    val showDialog = viewModel.showDialogBox.collectAsState().value

    Settings(currentTheme=currentTheme, onBackClick=onBackClick)

    if (showDialog) {
        Dialog(
            onDismissRequest = {viewModel.showThemeDialogBox(show = false)}
        ){
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp),
            ){
                Column(modifier = Modifier
                    .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(modifier = Modifier.padding(top = 40.dp))
                    Row{
                        Spacer(modifier = Modifier.padding(start = 50.dp))
                        RadioButton(
                            selected = currentTheme==SupportedThemes.LIGHT,
                            onClick = {viewModel.themeChoice(theme = SupportedThemes.LIGHT)}
                        )
                        Text(
                            text = "Light",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    Row{
                        Spacer(modifier = Modifier.padding(start = 50.dp))
                        RadioButton(
                            selected = currentTheme==SupportedThemes.DARK,
                            onClick = {viewModel.themeChoice(theme = SupportedThemes.DARK)}
                        )
                        Text(
                            text = "Dark",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                    Row{
                        Spacer(modifier = Modifier.padding(start = 50.dp))
                        RadioButton(
                            selected = currentTheme==SupportedThemes.SYSTEM_DEFAULT,
                            onClick = {viewModel.themeChoice(theme = SupportedThemes.SYSTEM_DEFAULT)}
                        )
                        Text(
                            text = "System Default",
                            modifier = Modifier.align(Alignment.CenterVertically)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Settings(
    currentTheme: SupportedThemes,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
    ) {
        TopBar(onBackClick)
        Spacer(modifier = Modifier.padding(20.dp))
        if (currentTheme == SupportedThemes.SYSTEM_DEFAULT)
            if (isSystemInDarkTheme()) DarkThemeButton() else LightThemeButton()
        else if (currentTheme == SupportedThemes.LIGHT) LightThemeButton()
        else DarkThemeButton()
    }
}
@Composable
fun TopBar (onBackClick: ()-> Unit){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .padding(horizontal = 15.dp)
    ){
        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Navigate back",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.aspectRatio(0.7f)
            )
        }
        Text(
            text="Settings",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.W600
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LightThemeButton(viewModel: SettingsViewModel = hiltViewModel()){
    Button(
        onClick = {viewModel.showThemeDialogBox(true)},
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.LightGray)
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = 20.dp),
    ){
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment =Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ){
            Image(
                painter = painterResource(R.drawable.sun),
                contentDescription = "LightThemeLogo",
                modifier = Modifier.aspectRatio(0.6f)
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(
                text = "Theme",
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.Default
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 70.dp))
            Text(
                text = "Light",
                color = colorResource(R.color.LightBlack),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.Default
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Composable
fun DarkThemeButton(viewModel: SettingsViewModel = hiltViewModel()){
    Button(
        onClick = {viewModel.showThemeDialogBox(true)},
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(R.color.LightGray)
        ),
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .padding(horizontal = 20.dp),
    ){
        Row (
            modifier = Modifier.fillMaxSize(),
            verticalAlignment =Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
        ){
            Image(
                painter = painterResource(R.drawable.dark),
                contentDescription = "LightThemeLogo",
                modifier = Modifier.aspectRatio(0.6f)
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Text(
                text = "Theme",
                color = colorResource(R.color.black),
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.Default
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 70.dp))
            Text(
                text = "Dark",
                color = colorResource(R.color.LightBlack),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily.Default
                )
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "",
                tint = Color.Black,
            )
        }
    }
}
