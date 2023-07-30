package curs.academy.domain.repository

import curs.academy.domain.models.User

interface UserRepository {

    fun insertUser(user : User)

    fun deleteUser(id : Int)

    fun updateLogin(newLogin : String, id : Int)

    fun updatePassword(newPassword : String, id : Int)
}