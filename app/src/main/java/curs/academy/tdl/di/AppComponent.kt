package curs.academy.tdl.di

import curs.academy.tdl.activity.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, DomainModel::class, DataModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}