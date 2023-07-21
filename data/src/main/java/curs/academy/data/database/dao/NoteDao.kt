package curs.academy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import curs.academy.data.models.NoteModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteModel)

    @Query("SELECT * FROM note_table")
    fun getAllNote() : List<NoteModel>

    @Query("DELETE FROM note_table WHERE id = :id")
    fun deleteNote(id : Int)

    @Query("UPDATE note_table SET text = :newText WHERE id = :id")
    fun updateNote(newText : String, id : Int)

    @Query("DELETE FROM note_table")
    fun deleteAllNote()

    @Query("SELECT * FROM note_table")
    fun getAllNoteFlow() : Flow<List<NoteModel>>
}