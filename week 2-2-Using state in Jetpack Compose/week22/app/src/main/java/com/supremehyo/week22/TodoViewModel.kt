package com.supremehyo.week22

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

    // LiveData 로 단방향 데이터 관리
    private var _todoItems = MutableLiveData(listOf<TodoItem>())
    val todoItems: LiveData<List<TodoItem>> = _todoItems

    //todolist에 데이터를 add 하는 동작
    fun addItem(item: TodoItem) {
        /* ... */
    }

    //todolist에 데이터를 remove 하는 동작
    fun removeItem(item: TodoItem) {
        /* ... */
    }
}