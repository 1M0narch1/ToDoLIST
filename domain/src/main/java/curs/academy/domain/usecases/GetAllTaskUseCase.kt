package curs.academy.domain.usecases

import curs.academy.domain.models.Task
import curs.academy.domain.repository.TaskRepository

class GetAllTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun execute() : List<Task>{
        return taskRepository.getAllTask()
    }

}