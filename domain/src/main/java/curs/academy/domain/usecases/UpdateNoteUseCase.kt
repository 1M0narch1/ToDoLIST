package curs.academy.domain.usecases

import curs.academy.domain.repository.NoteRepository

class UpdateNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(newText : String,id : Int){
        return noteRepository.updateNote(newText, id)
    }

}