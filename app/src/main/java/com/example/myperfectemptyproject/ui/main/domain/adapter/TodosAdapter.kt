package com.example.myperfectemptyproject.ui.main.domain.adapter


// The main task of adapters is to convert the entities used by the database
// and network clients to Domain module entities and back. This conversion has both pros and cons
// - Any data changes in one layer don’t affect other layers
// - Third-party dependencies as well as annotations required for a library don’t fall into other layers
// - There’s a possibility of multiple duplications
// - While changing the data you have to change the mapper

object TodosAdapter {

//    fun toStorage(todosColor: TodosColor) = Todos(
//        todosColor.completed, todosColor.id, todosColor.title, todosColor.userId
//    )
//
//    fun fromStorage(todos: Todos, color: Long = 0) = TodosColorImpl(
//        todos.completed, todos.id, todos.title, todos.userId, color
//    )
}
