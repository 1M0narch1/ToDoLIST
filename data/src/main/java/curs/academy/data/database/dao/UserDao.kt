package curs.academy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import curs.academy.data.models.TaskModel
import curs.academy.data.models.UserModel

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userModel: UserModel)

    @Query("DELETE FROM user_table WHERE id = :id")
    fun deleteUser(id : Int)

    @Query("UPDATE user_table SET login = :newLogin WHERE id = :id")
    fun updateLogin(newLogin : String, id: Int)

    @Query("UPDATE user_table SET password = :newPassword WHERE id = :id")
    fun updatePassword(newPassword : String, id : Int)

    @Query("SELECT userId FROM user_table WHERE login = :login AND password = :password")
    fun getUserId(login : String, password: String) : String

    @Query("SELECT * FROM user_table WHERE userId = :userId ")
    fun getUserByUserId(userId : String) : UserModel

    @Query("SELECT * FROM user_table")
    fun getAllUser() : List<UserModel>

    @Query("DELETE FROM user_table")
    fun deleteAll()
}