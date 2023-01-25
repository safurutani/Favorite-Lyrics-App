package com.example.lyricsformegha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lyricsformegha.model.LyricDataSource.lyrics
import com.example.lyricsformegha.model.Song
import com.example.lyricsformegha.ui.theme.LyricsForMeghaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LyricsForMeghaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(topBar = {LyricTopBar()}) {
                        SongList()
                    }
                }
            }
        }
    }
}

@Composable
fun LyricTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colors.background)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1
        )
    }
}
@Composable
fun SongCard(song: Song) {
    var expanded by remember { mutableStateOf(false)}
    Card (
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ){
        Column() {
            Row(horizontalArrangement = Arrangement.Start) {
                Image(
                    painter = painterResource(song.albumCover),
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                        .padding(16.dp),
                )
                Column(
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(top = 8.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(song.title), style = MaterialTheme.typography.h2)
                    Text(text = stringResource(song.artist), style = MaterialTheme.typography.h3)
                }
            }
            Divider(
                color = MaterialTheme.colors.background,
                thickness = 4.dp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp)
            )
            Row () {
                Spacer(modifier = Modifier.weight(1f))
                LyricButton(
                    expanded = expanded,
                    onClick = { expanded = !expanded }
                )
                Spacer(modifier = Modifier.weight(1f))
            }

            if (expanded) {
                Row(horizontalArrangement = Arrangement.Center) {
                    Text(
                        text = stringResource(song.lyric),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(
                            start = 16.dp,
                            top = 8.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun SongList() {
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxWidth()
    ) {
        items(lyrics) {
            SongCard(song = it)
        }
    }
}

@Composable
fun LyricButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if(expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = stringResource(R.string.expand_button_content_description),
            tint = MaterialTheme.colors.secondary,
            modifier = Modifier.size(36.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LyricsForMeghaTheme () {
        Scaffold(topBar = {LyricTopBar()}) {
            SongList()
        }
    }
}