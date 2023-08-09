package curs.academy.tdl.di

import curs.academy.domain.repository.AuthRepository
import curs.academy.domain.repository.NoteRepository
import curs.academy.domain.repository.TaskRepository
import curs.academy.domain.repository.UserRepository
import curs.academy.domain.usecases.Note.DeleteAllNoteUseCase
import curs.academy.domain.usecases.Note.DeleteNoteUseCase
import curs.academy.domain.usecases.Task.DeleteTaskUseCase
import curs.academy.domain.usecases.User.DeleteUserUseCase
import curs.academy.domain.usecases.Note.GetAllNoteFlowUseCase
import curs.academy.domain.usecases.Note.GetAllNoteUseCase
import curs.academy.domain.usecases.Task.GetAllTaskUseCase
import curs.academy.domain.usecases.Task.GetFlowAllTaskUseCase
import curs.academy.domain.usecases.User.GetUserIdUseCase
import curs.academy.domain.usecases.Note.InsertNoteUseCase
import curs.academy.domain.usecases.Task.InsertTaskUseCase
import curs.academy.domain.usecases.User.InsertUserUseCase
import curs.academy.domain.usecases.Auth.LoginUserFUseCase
import curs.academy.domain.usecases.Auth.RegistrUserFUseCase
import curs.academy.domain.usecases.User.UpdateLoginUseCase
import curs.academy.domain.usecases.Note.UpdateNoteUseCase
import curs.academy.domain.usecases.User.UpdatePasswordUseCase
import curs.academy.domain.usecases.Task.UpdateStateCompletedTaskUseCase
import curs.academy.domain.usecases.Task.UpdateTaskTextUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModel {

    //NoteUseCases
    @Provides
    fun provideInsertNoteUseCase(repository: NoteRepository) : InsertNoteUseCase {
        return InsertNoteUseCase(repository)
    }

    @Provides
    fun provideGetAllNoteFlowUseCase(repository: NoteRepository) : GetAllNoteFlowUseCase {
        return GetAllNoteFlowUseCase(repository)
    }

    @Provides
    fun provideGetAllNoteUseCase(repository: NoteRepository) : GetAllNoteUseCase {
        return GetAllNoteUseCase(repository)
    }

    @Provides
    fun provideUpdateNoteUseCase(repository: NoteRepository) : UpdateNoteUseCase {
        return UpdateNoteUseCase(repository)
    }

    @Provides
    fun provideDeleteAllNoteUseCase(repository: NoteRepository) : DeleteAllNoteUseCase {
        return DeleteAllNoteUseCase(repository)
    }

    @Provides
    fun provideDeleteNoteUseCase(repository: NoteRepository) : DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }

    //TaskUseCases
    @Provides
    fun provideInsertTaskUseCase(repository: TaskRepository) : InsertTaskUseCase {
        return InsertTaskUseCase(repository)
    }

    @Provides
    fun provideGetAllTaskFlowUseCase(repository: TaskRepository) : GetFlowAllTaskUseCase {
        return GetFlowAllTaskUseCase(repository)
    }

    @Provides
    fun provideGetAllTaskUseCase(repository: TaskRepository) : GetAllTaskUseCase {
        return GetAllTaskUseCase(repository)
    }

    @Provides
    fun provideUpdateTaskTextUseCase(repository: TaskRepository) : UpdateTaskTextUseCase {
        return UpdateTaskTextUseCase(repository)
    }

    @Provides
    fun provideUpdateStateCompletedTaskUseCase(repository: TaskRepository) : UpdateStateCompletedTaskUseCase {
        return UpdateStateCompletedTaskUseCase(repository)
    }

    @Provides
    fun provideDeleteTaskUseCase(repository: TaskRepository) : DeleteTaskUseCase {
        return DeleteTaskUseCase(repository)
    }

    //UserUseCases
    @Provides
    fun provideInsertUserUseCase(repository: UserRepository) : InsertUserUseCase {
        return InsertUserUseCase(repository)
    }

    @Provides
    fun provideDeleteUserUseCase(repository: UserRepository) : DeleteUserUseCase {
        return DeleteUserUseCase(repository)
    }

    @Provides
    fun provideUpdateLoginUseCase(repository: UserRepository) : UpdateLoginUseCase {
        return UpdateLoginUseCase(repository)
    }

    @Provides
    fun provideUpdatePasswordUseCase(repository: UserRepository) : UpdatePasswordUseCase {
        return UpdatePasswordUseCase(repository)
    }

    @Provides
    fun provideGetUserIdUseCase(repository: UserRepository) : GetUserIdUseCase {
        return GetUserIdUseCase(repository)
    }

    //auth
    @Provides
    fun provideLoginUserFUseCase(repository: AuthRepository) : LoginUserFUseCase {
        return LoginUserFUseCase(repository)
    }

    @Provides
    fun provideRegistrUserFUseCase(repository: AuthRepository) : RegistrUserFUseCase {
        return RegistrUserFUseCase(repository)
    }

}