package com.example.facultyofexactscience

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.example.facultyofexactscience.ui.theme.FacultyOfExactScienceTheme
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
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
fun Container() {

    Box(
        modifier = Modifier.fillMaxSize()
    ){

        Canvas(
            modifier = Modifier.fillMaxSize()) {
            val wavePath = Path().apply {
                moveTo(0f, size.height * 0.85f)
                quadraticBezierTo(
                    size.width * 0.25f, size.height * 0.9f,
                    size.width * 0.4f, size.height * 0.95f
                )
                quadraticBezierTo(
                    size.width * 0.8f, size.height * 0.65f,
                    size.width , size.height * 0.8f
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
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Header("Faculty of science",R.drawable.f_e_s)
            val items = listOf(
                R.drawable.faculte_map,
                R.drawable.faculte_map,
                R.drawable.faculte_map,
                R.drawable.faculte_map,
            )
            AutoCarousel(items)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                EventSlider()
                Qrcode()
            }

        }
    }
}


@Composable
fun Qrcode() {
    Column(
            modifier = Modifier.fillMaxWidth(0.5f),
          horizontalAlignment = Alignment.End,
    ) {
        Text(
            text = "Social Media",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier.width(150.dp).height(150.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF34693F))
                ,
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.qrcode_s_e),
                contentDescription = "QR",
                modifier = Modifier.fillMaxSize(0.9f),
            )
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
fun EventSlider() {

    val events = listOf(
    Event("Event 1", "Description 1", "Date 1", "Time 1"),
        Event("Event 2", "Description 2", "Date 2", "Time 2"),
        Event("Event 3", "Description 3", "Date 3", "Time 3"),
    )

    val eventIndex by remember { mutableIntStateOf(0) }


    Row(
        modifier = Modifier.fillMaxWidth(0.5f),
        horizontalArrangement = Arrangement.Center
    ) {
        events.forEachIndexed { index, event ->
            val isCurrent = index == eventIndex
            EventItem(event = event, isCurrent = isCurrent)
            Spacer(modifier = Modifier.width(8.dp)) // Add spacing between items
        }
    }
}

@Composable
fun EventItem(event: Event, isCurrent: Boolean) {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "08",
                    color = textColor,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Feb",
                    color = textColor,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.height(8.dp))


            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = event.title,
                    color = textColor,
                    style = MaterialTheme.typography.bodyLarge
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
fun AutoCarousel(
    items: List<Int>,
) {
    val lazyListState = rememberLazyListState()
    var currentIndex by remember { mutableIntStateOf(0) }


    LaunchedEffect(Unit) {
        while (true) {
            delay(5000)
            currentIndex = (currentIndex + 1) % items.size
            lazyListState.animateScrollToItem(currentIndex)
        }
    }


    Column(
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ){

        LazyRow(
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(items) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.4f)
                        .fillMaxHeight(0.6f)
                        .clip(RoundedCornerShape(20.dp))
                        .padding(end = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = item),
                        contentDescription = "Carousel Image",
                        contentScale = ContentScale.Fit,
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            items.forEachIndexed { index, _ ->
                val isSelected = index == currentIndex
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color = if (isSelected) Color.Green else Color.Gray,
                            shape = CircleShape
                        )
                        .padding(2.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
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

    ) {
        Header("UTMB",R.drawable.utmb_last2_rb)
        Divider()
        TimeDisplay()
        DepartmentMap()
        Footer()
    }
}

@Composable
fun Header(name: String = "",icon: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = "Logo",
            modifier = Modifier.size(100.dp)
        )
        Text(
            name,
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold
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
