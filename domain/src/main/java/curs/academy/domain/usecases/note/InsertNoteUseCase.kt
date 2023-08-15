package curs.academy.domain.usecases.note

import curs.academy.domain.models.Note
import curs.academy.domain.repository.NoteRepository

class InsertNoteUseCase(private val noteRepository: NoteRepository) {

    suspend fun execute(note : Note){
        return noteRepository.insertNote(note)
    }

}