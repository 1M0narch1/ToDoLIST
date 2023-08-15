package curs.academy.tdl.fragment.main.sup.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowId
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import curs.academy.domain.models.User
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.databinding.FragmentAddNoteBinding
import curs.academy.tdl.fragment.main.NoteFragment
import curs.academy.tdl.viewmodel.NoteViewModel
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

class AddNoteFragment : Fragment() {

    private lateinit var binding : FragmentAddNoteBinding
    @Inject
    lateinit var noteViewModelFactory: NoteViewModelFactory

    private var listNum: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddNoteBinding.inflate(layoutInflater)
        ToDoListApp.INSTANCE.appComponent.inject(this)
        val viewModelNote = ViewModelProvider(this, noteViewModelFactory)
            .get(NoteViewModel::class.java)
        arguments?.let {
            listNum = it.getString(ARG_PARAM1)
        }
        binding.button.setOnClickListener {
            viewModelNote.insertNote(binding.loginEditText.text.toString(), System.currentTimeMillis(),
                System.currentTimeMillis(), listNum!!)
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.conteiner,
                NoteFragment.newInstance(listNum!!)).commit()
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
        fun newInstance(listNum : String) =
            AddNoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, listNum)
                }
            }
    }
}