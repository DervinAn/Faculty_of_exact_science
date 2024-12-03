package com.example.facultyofexactscience

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.ShapeDefaults
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.example.facultyofexactscience.presentation.Caroussel.NewCaroussel
import com.example.facultyofexactscience.presentation.ui.Qrcode
import com.example.facultyofexactscience.ui.theme.FacultyOfExactScienceTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FacultyOfExactScienceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    Main()
                }
            }
        }
    }
}


@Composable
fun Main() {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        Sidebar()
        Container()
    }
}

@Composable
fun Canva(modifier: Modifier = Modifier) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val wavePath = Path().apply {
            moveTo(0f, size.height * 0.87f)
            quadraticBezierTo(
                size.width * 0.25f, size.height * 0.9f,
                size.width * 0.4f, size.height * 0.95f
            )
            quadraticBezierTo(
                size.width * 0.8f, size.height * 0.75f,
                size.width, size.height * 0.8f
            )
            lineTo(size.width, size.height)
            lineTo(0f, size.height)
            close()
        }

        drawPath(
            path = wavePath,
            color = Color(0xFF0B6623),
            style = Fill
        )
    }

}

@Composable
fun Container() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Canva()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header("Faculty of science", R.drawable.f_e_s)
            val items = listOf(
                R.drawable.faculte_map,
                R.drawable.faculte_map,
                R.drawable.faculte_map,
                R.drawable.faculte_map,
            )
            NewCaroussel(
                modifier = Modifier
                    .height(400.dp)
                    .padding(16.dp),

            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                EventSlider(
                    modifier = Modifier.weight(0.5f)
                )
                Qrcode(
                    modifier = Modifier.weight(0.5f)

                )
            }

        }
    }
}


data class Event(
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val time: String = ""
)


@Composable
fun EventSlider(
    modifier: Modifier = Modifier
) {
    val events = listOf(
        Event("Event 1", "Lorem ipsum dolor sit amet, conslit.", "Date 1", "Time 1"),
        Event("Event 2", "Lorem ipsum dolor sit amet, conslit.", "Date 2", "Time 2"),
        Event("Event 3", "Lorem ipsum dolor sit amet, conslit.", "Date 3", "Time 3"),
    )

    val eventIndex by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        events.forEachIndexed { index, event ->
            val isCurrent = index == eventIndex
            EventItem(event = event, isCurrent = isCurrent)
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun EventItem(
    event: Event,
    isCurrent: Boolean) {
    val backgroundColor = if (isCurrent) Color(0xFF34693F) else Color.White
    val textColor = if (isCurrent) Color.White else Color(0xFF34693F)
    val borderColor = if (isCurrent) Color(0xFF757D74) else Color(0xFF34693F)
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(135.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
            .border(1.dp, borderColor, RoundedCornerShape(20.dp))
            .padding(14.dp)
    ) {
        Column {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "08",
                    color = textColor,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Feb",
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(
                modifier = Modifier,

            ) {
                Text(
                    text = event.title,
                    color = textColor,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = event.description,
                    color = textColor,
                    style = MaterialTheme.typography.bodyLarge,

                    )
            }
        }
    }
}


@Composable
fun Sidebar() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.3f)
            .background(
                Color(0xFFF0FFED),
                shape = RoundedCornerShape(
                    topEnd = 20.dp,
                    bottomEnd = 20.dp
                )
            )
            .border(
                width = 2.dp, color = Color(0xffC1C9BE)
            )

    ) {
        Header("UTMB", R.drawable.utmb_last2_rb)
        Divider()
        TimeDisplay()
        DepartmentMap()
        Footer()
    }
}


@Composable
fun Header(name: String = "", icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
            //.padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "Logo",
            modifier = Modifier.size(90.dp)
        )
        Text(
            name,
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.SemiBold

            )
    }
}

@Composable
fun Divider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(Color.Gray)
    )
}

@Composable
fun TimeDisplay() {
    var currentTime by remember { mutableStateOf(getCurrentTime()) }
    var currentDate by remember { mutableStateOf(getCurrentDate()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = getCurrentTime()
            currentDate = getCurrentDate()
            delay(1000L)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = currentTime,
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = currentDate,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}


@Composable
fun DepartmentMap() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            "Department Map",
            fontWeight = FontWeight.Bold,
            fontSize = 29.sp
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.41f)
                .clip(shape = RoundedCornerShape(20.dp))
                .background(Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.faculte_map),
                contentDescription = "Map",
                contentScale = androidx.compose.ui.layout.ContentScale.Crop,
            )
        }
    }
}


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun Footer() {
    Spacer(modifier = Modifier.fillMaxHeight(0.1f))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.55f)
                .background(Color(0xFFD3E8D1))
                .border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp))
        ) {
            Text(
                "Lorem Epsun Lorem EpsunLoremEpsun",
                fontSize = 28.sp,
                modifier = Modifier.padding(16.dp)
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.id_logo),
            contentDescription = "ID",
            modifier = Modifier.size(80.dp),
            tint = Color.Unspecified
        )
    }
}


fun getCurrentDate(): String {
    val dateFormat = SimpleDateFormat("EEE, MMM d, yyyy", Locale.getDefault())
    return dateFormat.format(Date())
}

fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    return sdf.format(Date())
}

@Preview(
    name = "TV Preview",
    showBackground = true,
    widthDp = 1280,
    heightDp = 720
)

@Composable
fun GreetingPreview() {
    FacultyOfExactScienceTheme {
        Main()
    }
}
