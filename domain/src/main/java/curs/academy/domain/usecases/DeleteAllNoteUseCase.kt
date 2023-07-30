package curs.academy.domain.usecases

import curs.academy.domain.repository.NoteRepository

class DeleteAllNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(userId : Int){
        return noteRepository.deleteAllNote(userId)
    }

}