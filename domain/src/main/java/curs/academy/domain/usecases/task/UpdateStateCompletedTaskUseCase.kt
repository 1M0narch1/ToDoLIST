package curs.academy.domain.usecases.task

import curs.academy.domain.repository.TaskRepository

class UpdateStateCompletedTaskUseCase(private val taskRepository: TaskRepository) {

    suspend fun execute(newStateTaskCompleted : Boolean,id : Int){
        return taskRepository.updateStateCompletedTask(newStateTaskCompleted, id)
    }

}