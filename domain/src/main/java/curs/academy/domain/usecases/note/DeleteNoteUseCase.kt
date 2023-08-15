package curs.academy.domain.usecases.note

import curs.academy.domain.repository.NoteRepository

class DeleteNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(id : Int){
        return noteRepository.deleteNote(id)
    }

}