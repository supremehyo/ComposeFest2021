package com.supremehyo.week2_1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.supremehyo.week2_1.ui.theme.Week21Theme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week21Theme {
                TopAppBar(
                    title = {
                        Text(text = "Page title", maxLines = 2)
                    },
                    navigationIcon = {
                       // Icon(myNavIcon)
                    }
                )
                Surface(color = MaterialTheme.colors.background) {
                    PhotographerCard()
                }
            }
        }
    }
}

@Composable
fun PhotographerCard() {
    val context = LocalContext.current //컴포즈에서는 이렇게 context 알아온다.
    Row(
        modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colors.surface)
            .clickable(onClick =
            {
                Toast
                    .makeText(context, "dd", Toast.LENGTH_SHORT)
                    .show()
            })
    ){
        //이미지를 만들려고 그린 surface / 여기서 크기나 모양 그리고 컬러에대해 설정 할 수 있음.
        Surface(
            modifier = Modifier
                .size(50.dp)
                .padding(12.dp),
            shape = CircleShape,
            color =  MaterialTheme.colors.onSurface.copy(alpha = 0.2f)//표면의 알파값을 수정
        ){
            Button(
                onClick = {

                },
                content = {
                    Text("버튼" , color = Color.Black)
                }
            )
        }
        Column (
            //앞부분에 8dp만큼 패딩을 주고 수직방향 기준 중간에 오도록 요소를 정렬함.
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(Alignment.CenterVertically)
                )
        {
            Text("최효석", fontWeight = FontWeight.Bold) //글자의 굵기를 지정할수있음
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                // LocalContentAlpha 속성은 불투명도를 바꾸는 속성이라고함 즉 알파값
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }

    }
}

@Preview
@Composable
fun PhotographerCardPreview() {
    Week21Theme {
        PhotographerCard()
    }
}