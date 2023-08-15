package curs.academy.tdl.di


import curs.academy.domain.usecases.auth.LoginUserFUseCase
import curs.academy.domain.usecases.auth.RegistrUserFUseCase
import curs.academy.domain.usecases.note.DeleteAllNoteUseCase
import curs.academy.domain.usecases.note.DeleteNoteUseCase
import curs.academy.domain.usecases.note.GetAllNoteFlowUseCase
import curs.academy.domain.usecases.note.GetAllNoteUseCase
import curs.academy.domain.usecases.note.InsertNoteUseCase
import curs.academy.domain.usecases.note.UpdateNoteUseCase
import curs.academy.domain.usecases.task.DeleteTaskUseCase
import curs.academy.domain.usecases.task.GetAllTaskUseCase
import curs.academy.domain.usecases.task.GetFlowAllTaskUseCase
import curs.academy.domain.usecases.task.InsertTaskUseCase
import curs.academy.domain.usecases.task.UpdateStateCompletedTaskUseCase
import curs.academy.domain.usecases.task.UpdateTaskTextUseCase
import curs.academy.domain.usecases.user.DeleteUserUseCase
import curs.academy.domain.usecases.user.GetAllUserUseCase
import curs.academy.domain.usecases.user.GetUserByIdUseCase
import curs.academy.domain.usecases.user.GetUserIdUseCase
import curs.academy.domain.usecases.user.InsertUserUseCase
import curs.academy.domain.usecases.user.UpdateLoginUseCase
import curs.academy.domain.usecases.user.UpdatePasswordUseCase
import curs.academy.tdl.viewmodel.AuthViewModelFactory
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import curs.academy.tdl.viewmodel.TaskViewModelFactory
import curs.academy.tdl.viewmodel.UserViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    fun provideNoteViewModelFactory(insertNoteUseCase : InsertNoteUseCase,
                                    updateNoteUseCase: UpdateNoteUseCase,
                                    getAllNoteUseCase: GetAllNoteUseCase,
                                    getAllNoteFlowUseCase: GetAllNoteFlowUseCase,
                                    deleteNoteUseCase: DeleteNoteUseCase,
                                    deleteAllNoteUseCase: DeleteAllNoteUseCase
    ) : NoteViewModelFactory {
        return NoteViewModelFactory(insertNoteUseCase,
                                    updateNoteUseCase,
                                    getAllNoteUseCase,
                                    getAllNoteFlowUseCase,
                                    deleteNoteUseCase,
                                    deleteAllNoteUseCase)
    }

    @Provides
    fun provideTaskViewModelFactory(insertTaskUseCase : InsertTaskUseCase,
                                    updateStateCompletedTaskUseCase: UpdateStateCompletedTaskUseCase,
                                    updateTaskTextUseCase: UpdateTaskTextUseCase,
                                    getAllTaskUseCase: GetAllTaskUseCase,
                                    getFlowAllTaskUseCase: GetFlowAllTaskUseCase,
                                    deleteTaskUseCase: DeleteTaskUseCase
    ) : TaskViewModelFactory{
        return TaskViewModelFactory(insertTaskUseCase,
                                    updateStateCompletedTaskUseCase,
                                    updateTaskTextUseCase,
                                    getAllTaskUseCase,
                                    getFlowAllTaskUseCase,
                                    deleteTaskUseCase)
    }
    
    @Provides
    fun provideUserViewModelFactory(
                                     insertUserUseCase: InsertUserUseCase,
                                     deleteUserUseCase: DeleteUserUseCase,
                                     updateLoginUseCase: UpdateLoginUseCase,
                                     updatePasswordUseCase: UpdatePasswordUseCase,
                                     getUserIdUseCase: GetUserIdUseCase,
                                     getUserByIdUseCase: GetUserByIdUseCase,
                                     getAllUserUseCase: GetAllUserUseCase
    ) : UserViewModelFactory{
        return UserViewModelFactory(insertUserUseCase,
                                    deleteUserUseCase,
                                    updateLoginUseCase,
                                    updatePasswordUseCase,
                                    getUserIdUseCase,
                                    getUserByIdUseCase,
                                    getAllUserUseCase)
    }

    @Provides
    fun provideAuthViewModelFactory(
                                    loginUserFUseCase: LoginUserFUseCase,
                                    registrUserFUseCase: RegistrUserFUseCase
    ) : AuthViewModelFactory{
        return AuthViewModelFactory(
                                    loginUserFUseCase,
                                    registrUserFUseCase
        )
    }

    @Singleton
    class CurrentUserSingleton @Inject constructor() {
        var currentUserId: Int? = null
    }
}