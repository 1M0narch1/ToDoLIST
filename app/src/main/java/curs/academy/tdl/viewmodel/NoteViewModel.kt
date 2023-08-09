package curs.academy.tdl.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import curs.academy.domain.models.Note
import curs.academy.domain.usecases.Note.DeleteAllNoteUseCase
import curs.academy.domain.usecases.Note.DeleteNoteUseCase
import curs.academy.domain.usecases.Note.GetAllNoteFlowUseCase
import curs.academy.domain.usecases.Note.GetAllNoteUseCase
import curs.academy.domain.usecases.Note.InsertNoteUseCase
import curs.academy.domain.usecases.Note.UpdateNoteUseCase
import curs.academy.tdl.model.ValidationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel(private val insertNoteUseCase : InsertNoteUseCase,
                    private val updateNoteUseCase: UpdateNoteUseCase,
                    private val getAllNoteUseCase: GetAllNoteUseCase,
                    private val getAllNoteFlowUseCase: GetAllNoteFlowUseCase,
                    private val deleteNoteUseCase: DeleteNoteUseCase,
                    private val deleteAllNoteUseCase: DeleteAllNoteUseCase,
    ) : ViewModel() {

    private val  _state : MutableLiveData<ValidationState> = MutableLiveData(ValidationState.DataValid)
    val state : LiveData<ValidationState> = _state

    fun getNotesFlow(currentUserId: Int) : StateFlow<List<Note>> = getAllNoteFlowUseCase.execute(currentUserId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = listOf()
    )

    fun insertNote(text : String, dateOfCreation : Long, dateOfFutureExecution : Long, currentUserId: Int):Boolean{
        if(isDataValid(text, dateOfCreation, dateOfFutureExecution)){
            viewModelScope.launch(Dispatchers.IO) {
                insertNoteUseCase.execute(Note(text, dateOfCreation, dateOfFutureExecution, currentUserId))
            }
            return true
        }
        return false
    }

    fun updateNote(newText : String,id : Int):Boolean{
        if(newText.isNotEmpty() && id>=0){
            viewModelScope.launch(Dispatchers.IO) {
                updateNoteUseCase.execute(newText, id)
            }
            return true
        }
        return false
    }

    suspend fun getAllNote(currentUserId: Int) : List<Note>{
        return withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
            getAllNoteUseCase.execute(currentUserId)
        }
    }

    fun deleteNote(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.execute(id)
        }
    }

    fun deleteAllNote(currentUserId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllNoteUseCase.execute(currentUserId)
        }
    }

    private fun isDataValid(text : String, dateOfCreation : Long, dateOfFutureExecution : Long) : Boolean{
        if(text.isEmpty() && dateOfCreation>dateOfFutureExecution){
            _state.value = ValidationState.DataInvalid
            return false
        }
        return try {
            Note(text,
                dateOfCreation,
                dateOfFutureExecution,
                0)
            _state.value = ValidationState.DataValid
             true
        }catch (e :Exception){
            _state.value = ValidationState.DataInvalid
            false
        }

    }

}

class NoteViewModelFactory(private val insertNoteUseCase : InsertNoteUseCase,
                           private val updateNoteUseCase: UpdateNoteUseCase,
                           private val getAllNoteUseCase: GetAllNoteUseCase,
                           private val getAllNoteFlowUseCase: GetAllNoteFlowUseCase,
                           private val deleteNoteUseCase: DeleteNoteUseCase,
                           private val deleteAllNoteUseCase: DeleteAllNoteUseCase,
                           ): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteViewModel::class.java)){
            return NoteViewModel(insertNoteUseCase,
                updateNoteUseCase,
                getAllNoteUseCase,
                getAllNoteFlowUseCase,
                deleteNoteUseCase,
                deleteAllNoteUseCase) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel")
    }
}