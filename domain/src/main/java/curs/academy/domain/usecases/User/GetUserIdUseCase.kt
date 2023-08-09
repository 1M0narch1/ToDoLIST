package curs.academy.domain.usecases.User

import curs.academy.domain.models.User
import curs.academy.domain.repository.UserRepository

class GetUserIdUseCase(private val userRepository: UserRepository) {

    suspend fun execute(login : String, password : String) : Int{
        return userRepository.getUserId(login, password)
    }

}