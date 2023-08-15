package curs.academy.domain.usecases.note

import curs.academy.domain.repository.NoteRepository

class DeleteAllNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(userId : String){
        return noteRepository.deleteAllNote(userId)
    }

}