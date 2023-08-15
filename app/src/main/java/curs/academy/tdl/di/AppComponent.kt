package curs.academy.tdl.di

import curs.academy.tdl.activity.AuthActivity
import curs.academy.tdl.activity.MainActivity
import curs.academy.tdl.fragment.auth.ChoiceFragment
import curs.academy.tdl.fragment.auth.HelloFragment
import curs.academy.tdl.fragment.auth.LogInFragment
import curs.academy.tdl.fragment.auth.RegistrationFragment
import curs.academy.tdl.fragment.main.NoteFragment
import curs.academy.tdl.fragment.main.ProfileFragment
import curs.academy.tdl.fragment.main.TaskFragment
import curs.academy.tdl.fragment.main.sup.note.AddNoteFragment
import curs.academy.tdl.fragment.main.sup.note.UpdateNoteFragment
import curs.academy.tdl.fragment.main.sup.task.AddTaskFragment
import curs.academy.tdl.fragment.main.sup.task.UpdateTaskFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModel::class, DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(authActivity: AuthActivity)
    fun inject(helloFragment: HelloFragment)
    fun inject(choiceFragment: ChoiceFragment)
    fun inject(logInFragment: LogInFragment)
    fun inject(registrationFragment: RegistrationFragment)
    fun inject(noteFragment: NoteFragment)
    fun inject(profileFragment: ProfileFragment)
    fun inject(taskFragment: TaskFragment)
    fun inject(addNoteFragment: AddNoteFragment)
    fun inject(addTaskFragment: AddTaskFragment)
    fun inject(updateNoteFragment: UpdateNoteFragment)
    fun inject(updateTaskFragment: UpdateTaskFragment)
}