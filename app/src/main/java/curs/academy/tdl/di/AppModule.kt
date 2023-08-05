package curs.academy.tdl.di


import androidx.lifecycle.ViewModelProvider
import curs.academy.domain.usecases.DeleteAllNoteUseCase
import curs.academy.domain.usecases.DeleteNoteUseCase
import curs.academy.domain.usecases.DeleteTaskUseCase
import curs.academy.domain.usecases.DeleteUserUseCase
import curs.academy.domain.usecases.GetAllNoteFlowUseCase
import curs.academy.domain.usecases.GetAllNoteUseCase
import curs.academy.domain.usecases.GetAllTaskUseCase
import curs.academy.domain.usecases.GetFlowAllTaskUseCase
import curs.academy.domain.usecases.GetUserIdUseCase
import curs.academy.domain.usecases.InsertNoteUseCase
import curs.academy.domain.usecases.InsertTaskUseCase
import curs.academy.domain.usecases.InsertUserUseCase
import curs.academy.domain.usecases.UpdateLoginUseCase
import curs.academy.domain.usecases.UpdateNoteUseCase
import curs.academy.domain.usecases.UpdatePasswordUseCase
import curs.academy.domain.usecases.UpdateStateCompletedTaskUseCase
import curs.academy.domain.usecases.UpdateTaskTextUseCase
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import curs.academy.tdl.viewmodel.TaskViewModelFactory
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import dagger.Module
import dagger.Provides
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
                                    deleteTaskUseCase: DeleteTaskUseCase) : TaskViewModelFactory{
        return TaskViewModelFactory(insertTaskUseCase,
                                    updateStateCompletedTaskUseCase,
                                    updateTaskTextUseCase,
                                    getAllTaskUseCase,
                                    getFlowAllTaskUseCase,
                                    deleteTaskUseCase)
    }
    
    @Provides
    fun provideUserViewModelFactory(insertUserUseCase: InsertUserUseCase,
                                    deleteUserUseCase: DeleteUserUseCase,
                                    updateLoginUseCase: UpdateLoginUseCase,
                                    updatePasswordUseCase: UpdatePasswordUseCase,
                                    getUserIdUseCase: GetUserIdUseCase) : UserViewModelFactory{
        return UserViewModelFactory(insertUserUseCase,
                                    deleteUserUseCase,
                                    updateLoginUseCase,
                                    updatePasswordUseCase,
                                    getUserIdUseCase)
    }

}