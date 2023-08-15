package curs.academy.tdl.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import curs.academy.domain.models.Note
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.databinding.NoteItemBinding
import curs.academy.tdl.fragment.main.NoteFragment
import curs.academy.tdl.viewmodel.NoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class RViewNoteAdapter(private var noteList : List<Note>,
                        private val onDeleteNote: OnDeleteNote,
                        private val onUpdateNote: OnUpdateNote) : RecyclerView.Adapter<RViewNoteAdapter.RViewNoteHolder>() {

    class RViewNoteHolder(item: View, private val onDeleteNote: OnDeleteNote,
                          private val onUpdateNote: OnUpdateNote
    ) : RecyclerView.ViewHolder(item) {

        private val binding = NoteItemBinding.bind(item)

        fun bind(n : Note){
            binding.apply {
                n.apply {
                    textNote.text = text
                    timeNoteText.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(dateOfCreation).toString()
                    deleteButton.setOnClickListener{
                        onDeleteNote.onDeleteListener(n.id)
                    }
                    updateButton.setOnClickListener {
                        onUpdateNote.onUpdateListener(n.id)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewNoteAdapter.RViewNoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return RViewNoteHolder(view, onDeleteNote, onUpdateNote)
    }

    override fun onBindViewHolder(holder: RViewNoteHolder, position: Int) {
        holder.bind(noteList[position])
    }


    override fun getItemCount(): Int {
        return noteList.size
    }

    fun addNote(currentUserId : String){
        notifyDataSetChanged()
    }

    interface OnDeleteNote{
        fun onDeleteListener(id : Int)
    }

    interface OnUpdateNote{
        fun onUpdateListener(id : Int)
    }

}