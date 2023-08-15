package curs.academy.domain.usecases.note

import curs.academy.domain.models.Note
import curs.academy.domain.repository.NoteRepository

class GetAllNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(userId : String) : List<Note>{
        return noteRepository.getAllNote(userId)
    }
}