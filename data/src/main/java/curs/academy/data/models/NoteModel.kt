package curs.academy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import curs.academy.domain.models.Note


@Entity(tableName = "note_table")
data class NoteModel (
    val text : String,
    val dateOfCreation : Long,
    val dateOfFutureExecution : Long,
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
){
    companion object{
        fun map(note : Note): NoteModel = NoteModel(note.text, note.dateOfCreation, note.dateOfFutureExecution)
    }
}