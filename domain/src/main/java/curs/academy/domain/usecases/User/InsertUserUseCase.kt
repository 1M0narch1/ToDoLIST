package curs.academy.domain.usecases.User

import curs.academy.domain.models.Task
import curs.academy.domain.models.User
import curs.academy.domain.repository.TaskRepository
import curs.academy.domain.repository.UserRepository

class InsertUserUseCase(private val userRepository: UserRepository) {

    fun execute(user : User){
        return userRepository.insertUser(user)
    }

}