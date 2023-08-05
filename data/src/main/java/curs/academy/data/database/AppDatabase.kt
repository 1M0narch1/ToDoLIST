package curs.academy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import curs.academy.data.database.dao.NoteDao
import curs.academy.data.database.dao.TaskDao
import curs.academy.data.database.dao.UserDao
import curs.academy.data.models.NoteModel
import curs.academy.data.models.TaskModel
import curs.academy.data.models.UserModel

@Database(
    entities = [TaskModel::class, NoteModel::class, UserModel::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDao
    abstract fun getTaskDao() : TaskDao
    abstract fun getUserDao() : UserDao

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "ToDoListDatabase"

        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    AppDatabase.DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}