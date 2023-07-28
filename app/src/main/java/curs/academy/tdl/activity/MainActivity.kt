package curs.academy.tdl.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import curs.academy.data.repositoryimpl.NoteRepositoryImpl
import curs.academy.data.repositoryimpl.TaskRepositoryImpl
import curs.academy.domain.models.Task
import curs.academy.domain.usecases.DeleteAllNoteUseCase
import curs.academy.domain.usecases.DeleteNoteUseCase
import curs.academy.domain.usecases.DeleteTaskUseCase
import curs.academy.domain.usecases.GetAllNoteFlowUseCase
import curs.academy.domain.usecases.GetAllNoteUseCase
import curs.academy.domain.usecases.GetAllTaskUseCase
import curs.academy.domain.usecases.GetFlowAllTaskUseCase
import curs.academy.domain.usecases.InsertNoteUseCase
import curs.academy.domain.usecases.InsertTaskUseCase
import curs.academy.domain.usecases.UpdateNoteUseCase
import curs.academy.domain.usecases.UpdateStateCompletedTaskUseCase
import curs.academy.domain.usecases.UpdateTaskTextUseCase
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.viewmodel.NoteViewModel
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import curs.academy.tdl.viewmodel.TaskViewModel
import curs.academy.tdl.viewmodel.TaskViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}