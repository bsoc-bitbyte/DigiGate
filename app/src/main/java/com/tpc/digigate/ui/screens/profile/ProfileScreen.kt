package com.tpc.digigate.ui.screens.profile

import android.provider.Settings.Global.getString
import androidx.compose.foundation.Image

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tpc.digigate.R
import com.tpc.digigate.ui.screens.home.HomeScreenLayout
import com.tpc.digigate.ui.theme.DigiGateTheme
import com.tpc.digigate.ui.theme.MidGray
import com.tpc.digigate.ui.theme.PrimaryText
import com.tpc.digigate.ui.theme.SageDark

open class Student(
    val name: String,
    val rollNo: String,
    val image: Int,
    val email: String,
    val hostel: String,
    val phone: String,
    val branch: String
)

object SampleStudent : Student(
    name = "Neha Sharma",
    rollNo = "24BDS072",
    image = R.drawable.profile,
    email = "24bds072@iiitdmj.ac.in",
    hostel = "Maa Saraswati Hostel",
    phone = "+91 1234567890",
    branch = "Design"
)


@Composable
fun ProfileScreen(
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        item {
            Image(
                painter = painterResource(id = SampleStudent.image),
                contentDescription = "",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(percent = 50))
                    .aspectRatio(1f)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.onBackground,
                        shape = RoundedCornerShape(percent = 50)
                    )


            )
            Text(
                text = SampleStudent.name,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.W500,
                modifier = modifier.padding(top = 12.dp, bottom = 10.dp)
            )
            Text(
                text = SampleStudent.rollNo,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(bottom = 35.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.profile_info_header),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Row(Modifier.clickable(onClick = {})) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "",
                        modifier = Modifier.size(16.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = stringResource(R.string.edit),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }
            }
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 36.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    DataRow(R.drawable.email, stringResource(R.string.email), SampleStudent.email)

                    HorizontalDivider(color = MaterialTheme.colorScheme.background)

                    DataRow(
                        R.drawable.hostel,
                        stringResource(R.string.hostel),
                        SampleStudent.hostel
                    )
                    HorizontalDivider(color = MaterialTheme.colorScheme.background)

                    DataRow(R.drawable.phone, stringResource(R.string.phone), SampleStudent.phone)
                    HorizontalDivider(color = MaterialTheme.colorScheme.background)

                    DataRow(
                        R.drawable.branch,
                        stringResource(R.string.branch),
                        SampleStudent.branch
                    )
                }


            }
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                text = stringResource(R.string.utilities),
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium
            )
            Card(

                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clickable(onClick = {}),
                colors = CardDefaults.cardColors(
                    containerColor = MidGray
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Icon(
                            painter = painterResource(R.drawable.id),
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(Modifier.width(16.dp))
                        Text(
                            text = stringResource(R.string.view_id_card),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Icon(
                        imageVector = Icons.Filled.ArrowForwardIos,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun DataRow(icon: Int, label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Icon(
                painter = painterResource(icon),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }
        Text(
            text = value, style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

    }
}

@Preview(showBackground = true)
@Composable
fun LightProfileScreenPreview() {
    DigiGateTheme(darkTheme = false) {
        ProfileScreen(onSignOut = {})
    }
}

@Preview(showBackground = true)
@Composable
fun DarkProfileScreenPreview() {
    DigiGateTheme(darkTheme = true) {
        ProfileScreen(onSignOut = {})
    }
}