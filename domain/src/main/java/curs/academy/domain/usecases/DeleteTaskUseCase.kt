package curs.academy.domain.usecases

import curs.academy.domain.repository.TaskRepository

class DeleteTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun execute(id : Int){
        return taskRepository.deleteTask(id)
    }

}