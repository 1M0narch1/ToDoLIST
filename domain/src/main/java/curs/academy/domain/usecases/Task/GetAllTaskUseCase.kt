package curs.academy.domain.usecases.Task

import curs.academy.domain.models.Task
import curs.academy.domain.repository.TaskRepository

class GetAllTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun execute(userId : Int) : List<Task>{
        return taskRepository.getAllTask(userId)
    }

}