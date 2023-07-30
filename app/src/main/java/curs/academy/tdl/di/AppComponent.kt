package curs.academy.tdl.di

import dagger.Component

@Component(modules = [AppModule::class, DomainModel::class, DataModule::class])
interface AppComponent {
}