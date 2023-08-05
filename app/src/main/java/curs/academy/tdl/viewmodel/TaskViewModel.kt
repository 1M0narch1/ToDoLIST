package curs.academy.tdl.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import curs.academy.domain.models.Task
import curs.academy.domain.usecases.DeleteTaskUseCase
import curs.academy.domain.usecases.GetAllTaskUseCase
import curs.academy.domain.usecases.GetFlowAllTaskUseCase
import curs.academy.domain.usecases.InsertTaskUseCase
import curs.academy.domain.usecases.UpdateStateCompletedTaskUseCase
import curs.academy.domain.usecases.UpdateTaskTextUseCase
import curs.academy.tdl.model.ValidationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TaskViewModel(private val insertTaskUseCase : InsertTaskUseCase,
                    private val updateStateCompletedTaskUseCase: UpdateStateCompletedTaskUseCase,
                    private val updateTaskTextUseCase: UpdateTaskTextUseCase,
                    private val getAllTaskUseCase: GetAllTaskUseCase,
                    private val getFlowAllTaskUseCase: GetFlowAllTaskUseCase,
                    private val deleteTaskUseCase: DeleteTaskUseCase,
): ViewModel() {

    private val  _state : MutableLiveData<ValidationState> = MutableLiveData(ValidationState.DataValid)
    val state : LiveData<ValidationState> = _state

    fun getTasksFlow(currentUserId: Int) : StateFlow<List<Task>> = getFlowAllTaskUseCase.execute(currentUserId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = listOf()
    )

    fun insertTask(title : String,
                   text : String,
                   dateOfCreation : Long,
                   dateOfFutureExecution : Long,
                   taskCompleted:Boolean,
                   currentUserId: Int):Boolean{
        if(isDataValid(title, text, dateOfCreation, dateOfFutureExecution, taskCompleted)){
            viewModelScope.launch(Dispatchers.IO) {
                insertTaskUseCase.execute(Task(
                    title,
                    text,
                    dateOfCreation,
                    dateOfFutureExecution,
                    taskCompleted,
                    currentUserId))
            }
            return true
        }
        return false
    }

    fun updateStateCompletedTask(newStateTaskCompleted : Boolean,id : Int){
        viewModelScope.launch {
            updateStateCompletedTaskUseCase.execute(newStateTaskCompleted,id)
        }
    }

    fun updateTaskText(newText : String,id : Int):Boolean{
        if(newText.isNotEmpty() && id>=0){
            viewModelScope.launch(Dispatchers.IO) {
                updateTaskTextUseCase.execute(newText, id)
            }
            return true
        }
        return false
    }

    suspend fun getAllTask(currentUserId: Int) : List<Task>{
        return withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            getAllTaskUseCase.execute(currentUserId)
        }
    }

    fun deleteTask(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase.execute(id)
        }
    }

    private fun isDataValid(title : String,
                            text : String,
                            dateOfCreation : Long,
                            dateOfFutureExecution : Long,
                            taskCompleted:Boolean) : Boolean{
        if(text.isEmpty() && title.isEmpty() && dateOfCreation>dateOfFutureExecution){
            _state.value = ValidationState.DataInvalid
            return false
        }
        return try {
            Task(title,
                text,
                dateOfCreation,
                dateOfFutureExecution,
                taskCompleted,
                0)
            _state.value = ValidationState.DataValid
            true
        }catch (e :Exception){
            _state.value = ValidationState.DataInvalid
            false
        }

    }
}

class TaskViewModelFactory(private val insertTaskUseCase : InsertTaskUseCase,
                           private val updateStateCompletedTaskUseCase: UpdateStateCompletedTaskUseCase,
                           private val updateTaskTextUseCase: UpdateTaskTextUseCase,
                           private val getAllTaskUseCase: GetAllTaskUseCase,
                           private val getFlowAllTaskUseCase: GetFlowAllTaskUseCase,
                           private val deleteTaskUseCase: DeleteTaskUseCase,
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)){
            return TaskViewModel(insertTaskUseCase,
                updateStateCompletedTaskUseCase,
                updateTaskTextUseCase,
                getAllTaskUseCase,
                getFlowAllTaskUseCase,
                deleteTaskUseCase) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}