package curs.academy.domain.repository

import curs.academy.domain.models.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun getAllNote() : List<Note>

    suspend fun deleteNote(id : Int)

    suspend fun updateNote(newText : String, id : Int)

    suspend fun deleteAllNote()

    fun getAllNoteFlow() : Flow<List<Note>>
}