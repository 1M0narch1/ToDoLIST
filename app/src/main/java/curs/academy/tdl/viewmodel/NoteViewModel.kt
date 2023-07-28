package curs.academy.tdl.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import curs.academy.domain.usecases.DeleteAllNoteUseCase
import curs.academy.domain.usecases.DeleteNoteUseCase
import curs.academy.domain.usecases.GetAllNoteFlowUseCase
import curs.academy.domain.usecases.GetAllNoteUseCase
import curs.academy.domain.usecases.InsertNoteUseCase
import curs.academy.domain.usecases.UpdateNoteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import curs.academy.domain.models.Note
import curs.academy.tdl.model.ValidationState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NoteViewModel(private val insertNoteUseCase : InsertNoteUseCase,
                    private val updateNoteUseCase: UpdateNoteUseCase,
                    private val getAllNoteUseCase: GetAllNoteUseCase,
                    private val getAllNoteFlowUseCase: GetAllNoteFlowUseCase,
                    private val deleteNoteUseCase: DeleteNoteUseCase,
                    private val deleteAllNoteUseCase: DeleteAllNoteUseCase
    ) : ViewModel() {

    private val  _state : MutableLiveData<ValidationState> = MutableLiveData(ValidationState.DataValid)
    val state : LiveData<ValidationState> = _state

    val notes : StateFlow<List<Note>> = getAllNoteFlowUseCase.execute().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = listOf()
    )

    fun insertNote(text : String, dateOfCreation : Long, dateOfFutureExecution : Long):Boolean{
        if(isDataValid(text, dateOfCreation, dateOfFutureExecution)){
            viewModelScope.launch(Dispatchers.IO) {
                insertNoteUseCase.execute(Note(text, dateOfCreation, dateOfFutureExecution))
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

    suspend fun getAllNote() : List<Note>{
        return viewModelScope.async(Dispatchers.IO) {
            getAllNoteUseCase.execute()
        }.await()
    }

    fun deleteNote(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            deleteNoteUseCase.execute(id)
        }
    }

    fun deleteAllNote(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllNoteUseCase.execute()
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
                dateOfFutureExecution)
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
                           private val deleteAllNoteUseCase: DeleteAllNoteUseCase
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