package com.example.sem6

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Restaurant()
        }
    }
}

@Composable
fun Restaurant() {

    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreenss {
            showSplash = false
        }
    } else {
        Display()
    }
}

@Composable
fun SplashScreenss(onTimeout: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(3000)
        onTimeout()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Restaurant Logo")
    }
}

@Composable
fun Display() {

    val food = listOf("Apple", "Banana", "Mango")

    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        loading = false
    }

    if (loading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {

        LazyColumn(
            modifier = Modifier.padding(10.dp)
        ) {
            items(food) { item ->
                FoodItems(item)
            }
        }
    }
}

@Composable
fun FoodItems(item: String) {

    var rating by remember { mutableStateOf(0) }
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {

        Row(modifier = Modifier.padding(10.dp)) {

            Text(
                text = "🍔",
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            ) {

                Text(text = item)

                Text(text = "Price: ₹100")

                Spacer(modifier = Modifier.height(8.dp))

                CustomRatingBar1(
                    rating = rating,
                    onRatingChanged = {
                        rating = it
                        Toast.makeText(context, "Rated $it", Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }
    }
}
@Composable
fun CustomRatingBar1(
    rating: Int,
    maxRating: Int = 5,
    onRatingChanged: (Int) -> Unit
) {

    Row {
        for (i in 1..maxRating) {
            Text(
                text = "★",
                color = if (i <= rating) Color.Red else Color.Gray,
                modifier = Modifier
                    .size(45.dp)
                    .padding(4.dp)
                    .clickable {
                        onRatingChanged(i)
                    }
            )
        }
    }
}
