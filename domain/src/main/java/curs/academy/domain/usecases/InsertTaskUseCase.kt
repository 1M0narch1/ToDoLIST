package curs.academy.domain.usecases

import curs.academy.domain.models.Task
import curs.academy.domain.repository.TaskRepository

class InsertTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun execute(task: Task){
        return taskRepository.insertTask(task)
    }

}