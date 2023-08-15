package curs.academy.domain.usecases.task

import curs.academy.domain.repository.TaskRepository

class UpdateTaskTextUseCase(private val taskRepository: TaskRepository) {

    suspend fun execute(newText : String,id : Int){
        return taskRepository.updateTaskText(newText, id)
    }

}