package curs.academy.domain.usecases

import curs.academy.domain.repository.UserRepository

class UpdatePasswordUseCase(private val userRepository: UserRepository) {

    fun execute(newPassword : String,id : Int){
        return userRepository.updatePassword(newPassword, id)
    }

}