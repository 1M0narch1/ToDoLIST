package curs.academy.tdl

import android.app.Application
import curs.academy.data.database.AppDatabase
import curs.academy.tdl.di.AppComponent
import curs.academy.tdl.di.DaggerAppComponent

class ToDoListApp : Application() {

    val database by lazy{ AppDatabase.getDatabase(this) }

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder().build()
    }

    companion object{
        lateinit var INSTANCE : ToDoListApp
    }
}