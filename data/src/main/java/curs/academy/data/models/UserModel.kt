package curs.academy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import curs.academy.domain.models.User

@Entity(tableName = "user_table")
data class UserModel (
    val userId : Int,
    val login : String,
    val password : String,
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
){
    fun toUser():User = User(
        userId,
        login,
        password
    )

    companion object{
        fun map(user: User) : UserModel = UserModel(user.userId, user.login, user.password)
    }
}