package curs.academy.domain.usecases.Task

import curs.academy.domain.repository.TaskRepository

class UpdateTaskTextUseCase(private val taskRepository: TaskRepository) {

    suspend fun execute(newText : String,id : Int){
        return taskRepository.updateTaskText(newText, id)
    }

}