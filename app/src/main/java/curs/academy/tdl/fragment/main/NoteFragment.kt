package curs.academy.tdl.fragment.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import curs.academy.domain.models.Note
import curs.academy.domain.models.User
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.databinding.FragmentNoteBinding
import curs.academy.tdl.fragment.main.sup.note.AddNoteFragment
import curs.academy.tdl.fragment.main.sup.note.UpdateNoteFragment
import curs.academy.tdl.ui.RViewNoteAdapter
import curs.academy.tdl.viewmodel.NoteViewModel
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteFragment : Fragment(), RViewNoteAdapter.OnDeleteNote, RViewNoteAdapter.OnUpdateNote {

    private lateinit var binding : FragmentNoteBinding

    @Inject
    lateinit var noteViewModelFactory: NoteViewModelFactory

    private lateinit var adapter: RViewNoteAdapter

    private lateinit var viewModelNote : NoteViewModel

    private var listNote : List<Note> = listOf()

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("ListNum")
        }
        ToDoListApp.INSTANCE.appComponent.inject(this)

        viewModelNote = ViewModelProvider(this, noteViewModelFactory)
            .get(NoteViewModel::class.java)


        lifecycleScope.launch(Dispatchers.Main) {
            async(Dispatchers.IO) {
                listNote = viewModelNote.getAllNote(param1!!)
            }.await()
            adapter = RViewNoteAdapter(listNote, this@NoteFragment, this@NoteFragment)
            adapter.addNote(param1!!)
            val layoutManager = LinearLayoutManager(this@NoteFragment.requireContext())
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerView.layoutManager= layoutManager
            binding.recyclerView.adapter = adapter
        }

        binding.addNoteButton.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(id, AddNoteFragment.newInstance(param1!!)).commit()
        }

        binding.deleteNoteButton.setOnClickListener {
            viewModelNote.deleteAllNote(param1!!)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance( id: String) =
            NoteFragment().apply {
                arguments = Bundle().apply {
                    putString("ListNum", id)
                }
            }

    }

    override fun onDeleteListener(id: Int) {
        lifecycleScope.launch {
            viewModelNote.deleteNote(id)
        }
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.conteiner,
            NoteFragment.newInstance(param1!!)).commit()
    }

    override fun onUpdateListener(id: Int) {
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.conteiner,
            UpdateNoteFragment.newInstance(param1!!, id)).commit()
    }


}