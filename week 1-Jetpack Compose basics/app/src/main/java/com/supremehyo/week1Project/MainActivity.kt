package com.supremehyo.week1Project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supremehyo.week1Project.ui.theme.Week1JetpackComposeBasics2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week1JetpackComposeBasics2Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MyProject()
                }
            }
        }
    }
}

@Composable
private fun MyProject(names : List<String> = listOf("supreme" , "Hyo")) {
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier =  Modifier.padding(4.dp)) {
            for(name in names){
                Greeting(name)
                Greeting(name)
            }
            Text("First 열")
            Text("Second 열")
        }
    }
}

@Composable
fun Greeting(name: String) {
    //var expanded = false 컴포저블 내부의 변수에 할당할 수는 없다.
    //재구성이 일어나면 값이 새상태로 재설정 될 수 있다.
    // 재구성 상태에서 값을 유지하려면 remember를 사용해야한다.
    val expanded = remember { mutableStateOf(false) }
    //펼치기 접기할때 변경될 padding 의 값 48dp가 됐다가 expanded 변하면 0dp가 된다.
    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colors.primary
        ,modifier = Modifier.padding(vertical = 4.dp , horizontal = 8.dp))
    {
        Row(modifier =  Modifier.padding(12.dp)){
            Column(modifier = Modifier.weight(1f)
                .padding(bottom = extraPadding)) //여기서 extraPadding이 쓰이면서 재구성할때
            //패딩값이 변하게 된다.
            {
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(onClick = {
                expanded.value = !expanded.value
            })
            {
                Text(if (expanded.value)  "접기" else "펼치기")
            }
        }

    }
}

@Preview(showBackground = true , widthDp = 200)
@Composable
fun DefaultPreview() {
    Week1JetpackComposeBasics2Theme {
        MyProject()
    }
}