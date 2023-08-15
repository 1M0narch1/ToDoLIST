package curs.academy.tdl.fragment.main.sup.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.databinding.FragmentUpdateNoteBinding
import curs.academy.tdl.fragment.main.NoteFragment
import curs.academy.tdl.fragment.main.TaskFragment
import curs.academy.tdl.viewmodel.NoteViewModel
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UpdateNoteFragment : Fragment() {

    lateinit var binding: FragmentUpdateNoteBinding
    @Inject
    lateinit var noteViewModelFactory: NoteViewModelFactory

    private var listNum: String? = null
    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUpdateNoteBinding.inflate(layoutInflater)
        arguments?.let {
            listNum = it.getString(ARG_PARAM1)
            id = it.getInt(ARG_PARAM2)
        }
        ToDoListApp.INSTANCE.appComponent.inject(this)

        val viewModelNote = ViewModelProvider(this, noteViewModelFactory)
            .get(NoteViewModel::class.java)

        binding.buttonSaveNote.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                viewModelNote.updateNote(binding.loginEditText.text.toString(), id)
                Log.i("TAG", "${binding.loginEditText.text.toString()} ------ ${id}")
            }
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.conteiner,
                NoteFragment.newInstance(listNum!!)
            ).commit()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(listNum: String, id: Int) =
            UpdateNoteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, listNum)
                    putInt(ARG_PARAM2, id)
                }
            }
    }
}