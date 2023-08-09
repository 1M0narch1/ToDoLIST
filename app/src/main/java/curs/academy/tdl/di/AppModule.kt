package curs.academy.tdl.di


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
import curs.academy.domain.usecases.User.UpdateLoginUseCase
import curs.academy.domain.usecases.Note.UpdateNoteUseCase
import curs.academy.domain.usecases.User.UpdatePasswordUseCase
import curs.academy.domain.usecases.Task.UpdateStateCompletedTaskUseCase
import curs.academy.domain.usecases.Task.UpdateTaskTextUseCase
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import curs.academy.tdl.viewmodel.TaskViewModelFactory
import curs.academy.tdl.viewmodel.UserViewModelFactory
import dagger.Module
import dagger.Provides

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
    fun provideUserViewModelFactory(insertUserUseCase: InsertUserUseCase,
                                    deleteUserUseCase: DeleteUserUseCase,
                                    updateLoginUseCase: UpdateLoginUseCase,
                                    updatePasswordUseCase: UpdatePasswordUseCase,
                                    getUserIdUseCase: GetUserIdUseCase
    ) : UserViewModelFactory{
        return UserViewModelFactory(insertUserUseCase,
                                    deleteUserUseCase,
                                    updateLoginUseCase,
                                    updatePasswordUseCase,
                                    getUserIdUseCase)
    }

}