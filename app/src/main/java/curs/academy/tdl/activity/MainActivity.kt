package curs.academy.tdl.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import curs.academy.data.repositoryimpl.NoteRepositoryImpl
import curs.academy.data.repositoryimpl.TaskRepositoryImpl
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

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vm = NoteViewModel(InsertNoteUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            UpdateNoteUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            GetAllNoteUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            GetAllNoteFlowUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            DeleteNoteUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            DeleteAllNoteUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())))

        val vm1 = TaskViewModel(InsertTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            UpdateStateCompletedTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            UpdateTaskTextUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            GetAllTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            GetFlowAllTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            DeleteTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())))


        val vm2 =ViewModelProvider(this, NoteViewModelFactory(InsertNoteUseCase(
            NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            UpdateNoteUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            GetAllNoteUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            GetAllNoteFlowUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            DeleteNoteUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())),
            DeleteAllNoteUseCase(NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao()))))
            .get(NoteViewModel::class.java)

        val vm3 =ViewModelProvider(this, TaskViewModelFactory(
            InsertTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            UpdateStateCompletedTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            UpdateTaskTextUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            GetAllTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            GetFlowAllTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())),
            DeleteTaskUseCase(TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao()))
        )).get(TaskViewModel::class.java)


    }

}