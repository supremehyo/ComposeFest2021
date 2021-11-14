package com.supremehyo.week22

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codelabs.state.todo.AnimatedIconRow
import com.codelabs.state.todo.TodoEditButton
import com.codelabs.state.todo.TodoInputText
import kotlin.random.Random
//https://developer.android.com/codelabs/jetpack-compose-state?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fcompose%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fjetpack-compose-state#6
@Composable
fun TodoScreen(
    items: List<TodoItem>,
    currentlyEditing: TodoItem?,
    onAddItem: (TodoItem) -> Unit,
    onRemoveItem: (TodoItem) -> Unit,
    onStartEdit: (TodoItem) -> Unit,
    onEditItemChange: (TodoItem) -> Unit,
    onEditDone: () -> Unit
) {
    Column {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)// 내부 item 간의 패딩을 설정
        ) {
            items(items = items) {
                TodoRow(
                    todo = it,
                    onItemClicked = { onRemoveItem(it) },// 고차함수? 함수자체를 파라미터로 넘겨서 TodoRow 컴포저블에서 그대로 사용가능. MainActivity에서 viewmodel 에서 불러온 함수다.
                    modifier = Modifier.fillParentMaxWidth()
                )
            }
        }

        // For quick testing, a random item generator button
        Button(
            onClick = { onAddItem(generateRandomTodoItem()) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        ) {
            Text("Add random item")
        }
    }
}

@Composable
fun TodoRow(
    todo: TodoItem,
    onItemClicked: (TodoItem) -> Unit,
    modifier: Modifier = Modifier,
    iconAlpha: Float = remember(todo.id) { randomTint() }) //여기에 매계변수에 있다면
//로컬변수로 있을때는 컴포저블이 메모리에 생성될때 제어하지 못하지만 여기 있으면 제어가 가능하다.
{
    Row(
        modifier = modifier//상위 컴포저블에서 넣어준 modifier를 row에 적용하도록 하는 동작
            .clickable { onItemClicked(todo) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(todo.task)
      //  val iconAlpha = randomTint() //랜덤값 생성
        val iconAlpha: Float = remember(todo.id) { randomTint() }//todolist 를
        //재구성, 즉 데이터가 변경될때 화면을 다시 그릴때 색이 변하지 않고 id마다 유지시키고 싶다면 remember를 쓰는게 맞다.
        //(todo.id) 라는게 붙는데 이건 remeber의 키값이다. 해쉬맵의 key 같은 느낌이라고 생각하면 될거같다.
        Icon(
            imageVector = todo.icon.imageVector,
            tint = LocalContentColor.current.copy(alpha = iconAlpha), //랜덤한 숫자로 알파값 변경
            contentDescription = stringResource(id = todo.icon.contentDescription)
        )
    }
}

private fun randomTint(): Float {
    return Random.nextFloat().coerceIn(0.3f, 0.9f)
}

@Composable
fun TodoInputTextField(modifier: Modifier) {
    val (text, setText) = remember { mutableStateOf("") }
    TodoInputText(text, setText, modifier)
}


@Composable
fun TodoItemInput(onItemComplete: (TodoItem) -> Unit) {
    val (text, setText) = remember { mutableStateOf("") }
    val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default)}
    val iconsVisible = text.isNotBlank()
    val submit = {
        onItemComplete(TodoItem(text, icon))
        setIcon(TodoIcon.Default)
        setText("")
    }
    Column {
        Row(Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
        ) {
            TodoInputText(
                text = text,
                onTextChange = setText,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onImeAction = submit // pass the submit callback to TodoInputText
            )
            TodoEditButton(
                onClick = {
                    onItemComplete(TodoItem(text))
                    setText("")
                },
                text = "추가",
                modifier = Modifier.align(Alignment.CenterVertically),
                enabled = text.isNotBlank()
            )
        }
        if (iconsVisible) {
            AnimatedIconRow(icon, setIcon, Modifier.padding(top = 8.dp))
        } else {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
fun PreviewTodoScreen() {
    val items = listOf(
        TodoItem("Learn compose", TodoIcon.Event),
        TodoItem("Take the codelab"),
        TodoItem("Apply state", TodoIcon.Done),
        TodoItem("Build dynamic UIs", TodoIcon.Square)
    )
    TodoScreen(items, null, {}, {}, {}, {}, {})
}


@Preview
@Composable
fun PreviewTodoRow() {
    val todo = remember { generateRandomTodoItem() }
    TodoRow(todo = todo, onItemClicked = {}, modifier = Modifier.fillMaxWidth())
}
