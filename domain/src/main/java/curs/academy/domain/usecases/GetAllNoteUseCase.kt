package curs.academy.domain.usecases

import curs.academy.domain.models.Note
import curs.academy.domain.repository.NoteRepository

class GetAllNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(userId : Int) : List<Note>{
        return noteRepository.getAllNote(userId)
    }
}