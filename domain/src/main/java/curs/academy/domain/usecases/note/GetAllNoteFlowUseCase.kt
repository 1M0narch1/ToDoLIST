package curs.academy.domain.usecases.note

import curs.academy.domain.models.Note
import curs.academy.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNoteFlowUseCase(private val noteRepository: NoteRepository) {

    fun execute(userId : String) : Flow<List<Note>> {
        return noteRepository.getAllNoteFlow(userId)
    }

}