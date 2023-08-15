package curs.academy.tdl.di

import curs.academy.domain.repository.AuthRepository
import curs.academy.domain.repository.NoteRepository
import curs.academy.domain.repository.TaskRepository
import curs.academy.domain.repository.UserRepository
import curs.academy.domain.usecases.note.DeleteAllNoteUseCase
import curs.academy.domain.usecases.note.DeleteNoteUseCase
import curs.academy.domain.usecases.task.DeleteTaskUseCase
import curs.academy.domain.usecases.user.DeleteUserUseCase
import curs.academy.domain.usecases.note.GetAllNoteFlowUseCase
import curs.academy.domain.usecases.note.GetAllNoteUseCase
import curs.academy.domain.usecases.task.GetAllTaskUseCase
import curs.academy.domain.usecases.task.GetFlowAllTaskUseCase
import curs.academy.domain.usecases.user.GetUserIdUseCase
import curs.academy.domain.usecases.note.InsertNoteUseCase
import curs.academy.domain.usecases.task.InsertTaskUseCase
import curs.academy.domain.usecases.user.InsertUserUseCase
import curs.academy.domain.usecases.auth.LoginUserFUseCase
import curs.academy.domain.usecases.auth.RegistrUserFUseCase
import curs.academy.domain.usecases.user.UpdateLoginUseCase
import curs.academy.domain.usecases.note.UpdateNoteUseCase
import curs.academy.domain.usecases.user.UpdatePasswordUseCase
import curs.academy.domain.usecases.task.UpdateStateCompletedTaskUseCase
import curs.academy.domain.usecases.task.UpdateTaskTextUseCase
import curs.academy.domain.usecases.user.GetAllUserUseCase
import curs.academy.domain.usecases.user.GetUserByIdUseCase
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

    @Provides
    fun provideGetAllUserUseCase(repository: UserRepository) : GetAllUserUseCase {
        return GetAllUserUseCase(repository)
    }

    @Provides
    fun provideGetUserByIdUseCase(repository: UserRepository) : GetUserByIdUseCase {
        return GetUserByIdUseCase(repository)
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