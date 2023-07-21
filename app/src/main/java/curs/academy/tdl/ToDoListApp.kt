package curs.academy.tdl

import android.app.Application
import curs.academy.data.database.AppDatabase

class ToDoListApp : Application() {

    val database by lazy{ AppDatabase.getDatabase(this) }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object{
        lateinit var INSTANCE : ToDoListApp
    }
}