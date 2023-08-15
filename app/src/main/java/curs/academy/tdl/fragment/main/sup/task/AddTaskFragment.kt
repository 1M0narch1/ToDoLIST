package curs.academy.tdl.fragment.main.sup.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import curs.academy.domain.models.User
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.databinding.FragmentAddTaskBinding
import curs.academy.tdl.fragment.main.NoteFragment
import curs.academy.tdl.fragment.main.TaskFragment
import curs.academy.tdl.viewmodel.NoteViewModel
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import curs.academy.tdl.viewmodel.TaskViewModel
import curs.academy.tdl.viewmodel.TaskViewModelFactory
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

private const val ARG_PARAM1 = "param1"

class AddTaskFragment : Fragment() {

    private lateinit var binding : FragmentAddTaskBinding
    @Inject
    lateinit var taskViewModelFactory: TaskViewModelFactory

    private var listNum: String? = null

    private var date : Long = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentAddTaskBinding.inflate(layoutInflater)
        date = binding.calendar.date
        ToDoListApp.INSTANCE.appComponent.inject(this)

        val viewModelTask = ViewModelProvider(this, taskViewModelFactory)
            .get(TaskViewModel::class.java)

        arguments?.let {
            listNum = it.getString(ARG_PARAM1)
        }
        binding.calendar.setOnDateChangeListener { view, year, month, dayOfMonth ->
            lifecycleScope.launch {
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth, 0, 0, 0)
                date = calendar.timeInMillis/1000
            }
        }
        binding.button.setOnClickListener {
            viewModelTask.insertTask("Task", binding.taskEditText.text.toString(),
                System.currentTimeMillis(), date, false,
                listNum!!)
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.conteiner,
                TaskFragment.newInstance(listNum!!)).commit()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return binding.root
    }

    companion object {

        @JvmStatic fun newInstance(listNum: String) =
                AddTaskFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, listNum)
                    }
                }
    }
}