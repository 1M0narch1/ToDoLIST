package curs.academy.domain.repository

import curs.academy.domain.models.User
import curs.academy.domain.usecases.UpdatePasswordUseCase

interface UserRepository {

    fun insertUser(user : User)

    fun deleteUser(id : Int)

    fun updateLogin(newLogin : String, id : Int)

    fun updatePassword(newPassword : String, id : Int)

    suspend fun getUserId(login : String, password : String) : Int
}