package curs.academy.tdl.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.viewmodel.NoteViewModel
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import curs.academy.tdl.viewmodel.TaskViewModel
import curs.academy.tdl.viewmodel.TaskViewModelFactory
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var noteViewModelFactory: NoteViewModelFactory
    @Inject
    lateinit var taskViewModelFactory: TaskViewModelFactory
    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ToDoListApp.INSTANCE.appComponent.inject(this)

        val viewModelUser = ViewModelProvider(this, userViewModelFactory)
            .get(UserViewModel::class.java)
        val viewModelTask = ViewModelProvider(this, taskViewModelFactory)
            .get(TaskViewModel::class.java)
        val viewModelNote = ViewModelProvider(this, noteViewModelFactory)
            .get(NoteViewModel::class.java)
    }

}