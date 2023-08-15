package curs.academy.domain.repository

import curs.academy.domain.models.Note
import kotlinx.coroutines.flow.Flow


interface NoteRepository {

    suspend fun insertNote(note: Note)

    suspend fun getAllNote(userId : String) : List<Note>

    suspend fun deleteNote(id : Int)

    suspend fun updateNote(newText : String, id : Int)

    suspend fun deleteAllNote(userId : String)

    fun getAllNoteFlow(userId : String) : Flow<List<Note>>
}