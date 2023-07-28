package curs.academy.domain.usecases

import curs.academy.domain.models.Note
import curs.academy.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNoteFlowUseCase(private val noteRepository: NoteRepository) {

    fun execute() : Flow<List<Note>> {
        return noteRepository.getAllNoteFlow()
    }

}