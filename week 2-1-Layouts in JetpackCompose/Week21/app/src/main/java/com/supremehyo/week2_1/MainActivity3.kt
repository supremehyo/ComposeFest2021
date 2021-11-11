package com.supremehyo.week2_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.supremehyo.week2_1.ui.theme.Week21Theme
import kotlinx.coroutines.launch

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week21Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LayoutsCodelab()
                }
            }
        }
    }
}

@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ){ innerPadding ->
        Column() {
            BodyContent(
                Modifier
                    .padding(innerPadding).padding(20.dp))
            SimpleList()
        }
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Week21Theme {
        LayoutsCodelab()
    }
}

@Composable
fun SimpleList() {
    /*
    val scrollState = rememberScrollState()
    //세로 스크롤 / scrollState 스크롤의 위치기억
    Column(Modifier.verticalScroll(scrollState)) {
        repeat(100) {
            Text("Item #$it")
        }
    }*/
    val listSize = 100
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberLazyListState()
    Column {
        Row {
            Button(onClick = {
                //스크롤하는 동안 목록 렌더링을 차단하는 것을 방지하기
                // 위해 스크롤 API를 코루틴에서 사용함.
                coroutineScope.launch {
                    //animateScrollToItem 스크롤을 애니메이션을 적용하며 0번 인덱스로 이동시킴
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("젤 위로 올라가기")
            }
            Button(onClick = {
                coroutineScope.launch {
                    //마지막 인덱스 위치로 스크롤 이동함
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }) {
                Text("젤 아래로 내려가기")
            }
        }
        //LazyColumn = 리사이클러뷰
        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(it)
            }
        }
    }
    
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {

        Image(
            //Coil의 rememberImagePainter를 이용해서 이미지를 호출
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}