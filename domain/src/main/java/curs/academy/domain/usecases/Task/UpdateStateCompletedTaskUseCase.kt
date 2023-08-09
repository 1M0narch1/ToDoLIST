package curs.academy.domain.usecases.Task

import curs.academy.domain.repository.TaskRepository

class UpdateStateCompletedTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun execute(newStateTaskCompleted : Boolean,id : Int){
        return taskRepository.updateStateCompletedTask(newStateTaskCompleted, id)
    }

}