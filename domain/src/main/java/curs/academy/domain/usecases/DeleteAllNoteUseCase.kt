package curs.academy.domain.usecases

import curs.academy.domain.repository.NoteRepository

class DeleteAllNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(){
        return noteRepository.deleteAllNote()
    }

}