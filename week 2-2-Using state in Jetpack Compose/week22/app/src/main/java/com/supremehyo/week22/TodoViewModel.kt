package com.supremehyo.week22

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    // LiveData 로 단방향 데이터 관리
  //  private var _todoItems = MutableLiveData(listOf<TodoItem>())
  //  val todoItems: LiveData<List<TodoItem>> = _todoItems
    // private state
    private var currentEditPosition by mutableStateOf(-1)

    var todoItems = mutableStateListOf<TodoItem>()
        private set

    // state
    val currentEditItem: TodoItem?
        get() = todoItems.getOrNull(currentEditPosition)

    //todolist에 데이터를 add 하는 동작
    fun addItem(item: TodoItem) {
        todoItems.add(item)
    }

    //todolist에 데이터를 remove 하는 동작
    fun removeItem(item: TodoItem) {
        todoItems.remove(item)
        onEditDone()

    }

    // event: onEditItemSelected
    fun onEditItemSelected(item: TodoItem) {
        currentEditPosition = todoItems.indexOf(item)
    }

    // event: onEditDone
    fun onEditDone() {
        currentEditPosition = -1
    }

    // event: onEditItemChange
    fun onEditItemChange(item: TodoItem) {
        val currentItem = requireNotNull(currentEditItem)
        require(currentItem.id == item.id) {
            "You can only change an item with the same id as currentEditItem"
        }

        todoItems[currentEditPosition] = item
    }
}