package curs.academy.tdl.fragment.main.sup.task

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
import curs.academy.tdl.databinding.FragmentUpdateTaskBinding
import curs.academy.tdl.fragment.main.NoteFragment
import curs.academy.tdl.viewmodel.TaskViewModel
import curs.academy.tdl.viewmodel.TaskViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class UpdateTaskFragment : Fragment() {
    @Inject
    lateinit var taskViewModelFactory: TaskViewModelFactory

    private lateinit var binding : FragmentUpdateTaskBinding

    private var listNum: String? = null
    private var id: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUpdateTaskBinding.inflate(layoutInflater)
        ToDoListApp.INSTANCE.appComponent.inject(this)

        val viewModelTask = ViewModelProvider(this, taskViewModelFactory)
            .get(TaskViewModel::class.java)
        arguments?.let {
            listNum = it.getString(ARG_PARAM1)
            id = it.getInt(ARG_PARAM2)
        }
        binding.buttonSaveTask.setOnClickListener {
            Log.i("TAG", "id")
            lifecycleScope.launch(Dispatchers.IO) {
                viewModelTask.updateTaskText(binding.loginEditText.text.toString(), id)
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
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(listNum: String, id: Int) =
            UpdateTaskFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, listNum)
                    putInt(ARG_PARAM2, id)
                }
            }
    }
}