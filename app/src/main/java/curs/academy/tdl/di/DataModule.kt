package curs.academy.tdl.di

import curs.academy.data.repositoryimpl.NoteRepositoryImpl
import curs.academy.data.repositoryimpl.TaskRepositoryImpl
import curs.academy.data.repositoryimpl.UserRepositoryImpl
import curs.academy.domain.repository.NoteRepository
import curs.academy.domain.repository.TaskRepository
import curs.academy.domain.repository.UserRepository
import curs.academy.tdl.ToDoListApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {
    //repositories
    @Provides
    fun provideNoteRepository() : NoteRepository {
        return NoteRepositoryImpl(ToDoListApp.INSTANCE.database.getNoteDao())
    }

    @Provides
    fun provideTaskRepository() : TaskRepository {
        return TaskRepositoryImpl(ToDoListApp.INSTANCE.database.getTaskDao())
    }

    @Provides
    fun provideUserRepository() : UserRepository {
        return UserRepositoryImpl(ToDoListApp.INSTANCE.database.getUserDao())
    }
}