package com.supremehyo.week1Project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
                   // MyProject()
                    Greetings()
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
private fun Greetings(names: List<String> = List(1000) { "$it" } ) {
    //recycler랑 비슷하다 훨씬 간편하게 레이아웃을 구성하게 해준다.
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    //var expanded = false 컴포저블 내부의 변수에 할당할 수는 없다.
    //재구성이 일어나면 값이 새상태로 재설정 될 수 있다.
    // 재구성 상태에서 값을 유지하려면 remember를 사용해야한다.
   // val expanded = remember { mutableStateOf(false) }

    //rememberSaveable 은 화면이 돌아가거나 다크모드로 변경되는 상황에도 값을 유지한다.
    var expanded by remember { mutableStateOf(false) }
    //펼치기 접기할때 변경될 padding 의 값 48dp가 됐다가 expanded 변하면 0dp가 된다.
    //val extraPadding = if (expanded.value) 48.dp else 0.dp

    //애니메이션 효과 추가하기
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary
        ,modifier = Modifier.padding(vertical = 4.dp , horizontal = 8.dp))
    {
        Row(modifier =  Modifier.padding(12.dp)){
            Column(modifier = Modifier.weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) //여기서 extraPadding이 쓰이면서 재구성할때
            //패딩값이 변하게 된다.  coerceAtLeast은 패딩이 0dp 이하로 가지 않게 제약을 주는것
            {
                Text(text = "Hello, ")
                Text(text = name , style = MaterialTheme.typography.h4)
            }
            OutlinedButton(
                onClick = { expanded = !expanded }
            )
            {
                Text(if (expanded)  "접기" else "펼치기")
            }
        }

    }
}

@Preview(showBackground = true , widthDp = 200)
@Composable
fun DefaultPreview() {
    Week1JetpackComposeBasics2Theme {
        Greetings()
     //   MyProject()
    }
}