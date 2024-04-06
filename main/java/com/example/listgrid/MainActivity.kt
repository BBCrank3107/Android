package com.example.listgrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.listgrid.data.DataSource.videos
import com.example.listgrid.model.Video
import com.example.listgrid.ui.theme.ListGridTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListGridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ListGridApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListGridApp() {
    Scaffold(
        topBar = {
            TopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(videos) {
                videoItem(
                    video = it,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun videoItem(video: Video, modifier: Modifier) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = modifier
                .background(Color.White)
        ) {
            VideoImage(video.imageVideo)
            VideoInfo(video.imageAvt, video.videoName, video.chanelName, video.view, video.time)
        }
    }
}

@Composable
fun formatTime(time: Int): String {
    return when {
        time > 365 -> "${time / 365} năm trước"
        time > 30 -> "${time / 30} tháng trước"
        else -> "$time ngày trước"
    }
}

@Composable
fun formatView(view: Int): String {
    return when {
        view >= 1000000 -> "${view / 1000000} Tr lượt xem"
        view >= 1000 -> "${view / 1000} N lượt xem"
        else -> "$view lượt xem"
    }
}

@Composable
fun shortenChanelName(chanelName: String): String {
    return if (chanelName.length > 15) {
        chanelName.substring(0, 12) + "..."
    } else {
        chanelName
    }
}

@Composable
fun shortenVideoName(videoName: String): String {
    return if (videoName.length > 59) {
        videoName.substring(0, 59) + "..."
    } else {
        videoName
    }
}

@Composable
fun VideoInfo(
    @DrawableRes imageAvt: Int,
    @StringRes videoName: Int,
    @StringRes chanelName: Int,
    view: Int,
    time: Int,
    modifier: Modifier = Modifier
) {
    val formattedTime = formatTime(time)
    val formattedView = formatView(view)
    val shortenedChanelName = shortenChanelName(stringResource(chanelName))
    val shortenedVideoName = shortenVideoName(stringResource(videoName))

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(8.dp),
    ) {
        Box(
            modifier = modifier
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CircleShape
                )
        ) {
            Image(
                painter = painterResource(imageAvt),
                contentDescription = null
            )
        }
        Box(
            modifier = modifier
                .padding(start = 8.dp)
                .width(280.dp)
        ) {
            Column {
                Text(
                    text = shortenedVideoName
                )
                Row(
                    modifier = modifier
                ) {
                    Text(
                        text = shortenedChanelName,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = modifier
                            .padding(end = 3.dp)
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = modifier
                            .padding(end = 3.dp)
                    )
                    Text(
                        text = formattedView,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = modifier
                            .padding(end = 3.dp)
                    )
                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = modifier
                            .padding(end = 3.dp)
                    )
                    Text(
                        text = formattedTime,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
        Box(
            modifier = modifier
        ) {
            Icon(
                painter = painterResource(R.drawable.more),
                contentDescription = null
            )
        }
    }
}

@Composable
fun VideoImage(
    @DrawableRes videoImage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        modifier = modifier
            .size(width = 400.dp, height = 220.dp)
            .clip(MaterialTheme.shapes.small),
        painter = painterResource(videoImage),
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 110.dp, height = 35.dp)
                        .padding(start = 16.dp)
                )
            }

            Box(
                modifier = Modifier
            ) {
                Row {
                    Icon(
                        painter = painterResource(R.drawable.share),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.notifications),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                    Icon(
                        painter = painterResource(R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(24.dp)
                    )
                }
            }
        }
        Box (
            modifier = modifier
                .background(Color.White)
        ){
            LazyRow(
                modifier = modifier,
                contentPadding = PaddingValues(start = 8.dp, top = 0.dp, bottom = 8.dp, end = 8.dp)
            ) {
                item {
                    CategoryItem(categoryName = "", iconRes = R.drawable.compass)
                }
                items(categories) { category ->
                    CategoryItem(categoryName = category)
                }
            }
        }
    }
}

@Composable
fun CategoryItem(categoryName: String, @DrawableRes iconRes: Int? = null) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .height(40.dp)
            .clickable {}
    ) {
        if (iconRes != null) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                modifier = Modifier.padding(8.dp)
            )
        }
        Text(
            text = categoryName,
            modifier = Modifier.padding(8.dp)
        )
    }
}

val categories = listOf("Tất cả", "Thể thao", "Âm nhạc", "Hài hước", "Học tập", "Làm đẹp")


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ListGridTheme {
        ListGridApp()
    }
}