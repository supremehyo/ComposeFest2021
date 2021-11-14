package com.supremehyo.week22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.supremehyo.week22.ui.theme.Week22Theme

//https://witcheryoon.tistory.com/215?category=969418
//https://gift123.tistory.com/34 개념참고
class MainActivity : ComponentActivity() {

    private  val TodoViewModel by viewModels<TodoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week22Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //여기에 시작
                    TodoActvitiyScreen(TodoViewModel)
                }
            }
        }
    }
}

// ViewModel에 저장된 상태와 TodoScreen프로젝트에
// 이미 정의된 컴포저블 간의 다리 역할을 합니다
@Composable
fun TodoActvitiyScreen(todoViewModel: TodoViewModel) {
    //viewmodel의 livedata 를 이런식으로 가져와서 쓸 수 있게 만든다. 단방향 통신을 위해 필요.
    //Compose에서 LiveData 같은 다른 observable type을 사용할 경우  Composable에서 LiveData<T>.observeAsState() 같은 Composable 함수를 사용하여
    //그 type을 읽어오려면 유형을 State<T>로 변환해야 한다. observeAsState는 LiveData<T>를 관찰하고 LiveData가 변경될 때마다 업데이트되는 State<T> 객체를 반환한다.
   // val items: List<TodoItem> by todoViewModel.todoItems.observeAsState(listOf())
    //다른 파일에서 만들어둔 컴포저블을 이렇게 불러와서 쓸 수 있음.
    TodoScreen(
        items = todoViewModel.todoItems,
        currentlyEditing = todoViewModel.currentEditItem,
        onAddItem = todoViewModel::addItem,
        onRemoveItem = todoViewModel::removeItem,
        onStartEdit = todoViewModel::onEditItemSelected,
        onEditItemChange = todoViewModel::onEditItemChange,
        onEditDone = todoViewModel::onEditDone
        /*
        items = items,
        onAddItem = { todoViewModel.addItem(it) },
        onRemoveItem = { todoViewModel.removeItem(it) }*/
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Week22Theme {
    //    TodoActvitiyScreen(TodoViewModel)
    }
}