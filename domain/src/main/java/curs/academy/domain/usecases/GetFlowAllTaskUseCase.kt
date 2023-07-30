package curs.academy.domain.usecases

import curs.academy.domain.models.Task
import curs.academy.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetFlowAllTaskUseCase(private val taskRepository: TaskRepository) {

    fun execute(userId : Int) : Flow<List<Task>> {
        return taskRepository.getAllTaskFlow(userId)
    }

}