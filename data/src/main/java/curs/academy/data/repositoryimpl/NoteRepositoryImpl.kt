package curs.academy.data.repositoryimpl

import curs.academy.data.database.dao.NoteDao
import curs.academy.data.models.NoteModel
import curs.academy.domain.models.Note
import curs.academy.domain.repository.NoteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {

    private val job = SupervisorJob()
    private val noteScope = CoroutineScope(job+Dispatchers.IO)

    override suspend fun insertNote(note: Note) {
        noteScope.launch {
            noteDao.insertNote(NoteModel.map(note))
        }
    }

    override suspend fun getAllNote(userId : String): List<Note> {
        return noteScope.async {
            noteDao.getAllNote(userId).map { it.toNote() }
        }.await()
    }

    override suspend fun deleteNote(id: Int) {
        noteScope.launch {
            noteDao.deleteNote(id)
        }
    }

    override suspend fun updateNote(newText: String, id: Int) {
        noteScope.launch {
            noteDao.updateNote(newText, id)
        }
    }

    override suspend fun deleteAllNote(userId : String) {
        noteScope.launch {
            noteDao.deleteAllNote(userId)
        }
    }

    override fun getAllNoteFlow(userId : String): Flow<List<Note>> {
        return noteDao.getAllNoteFlow(userId).flatMapConcat { list ->
            flowOf(list.map { it.toNote() })
        }
    }
}