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
import curs.academy.domain.models.Task
import curs.academy.domain.models.User
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.databinding.FragmentTaskBinding
import curs.academy.tdl.fragment.main.sup.note.AddNoteFragment
import curs.academy.tdl.fragment.main.sup.note.UpdateNoteFragment
import curs.academy.tdl.fragment.main.sup.task.AddTaskFragment
import curs.academy.tdl.fragment.main.sup.task.UpdateTaskFragment
import curs.academy.tdl.ui.RViewNoteAdapter
import curs.academy.tdl.ui.RViewTaskAdapter
import curs.academy.tdl.viewmodel.NoteViewModel
import curs.academy.tdl.viewmodel.NoteViewModelFactory
import curs.academy.tdl.viewmodel.TaskViewModel
import curs.academy.tdl.viewmodel.TaskViewModelFactory
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

class TaskFragment : Fragment(), RViewTaskAdapter.TaskButtons{

    private lateinit var binding : FragmentTaskBinding

    @Inject
    lateinit var taskViewModelFactory: TaskViewModelFactory

    private  lateinit var viewModelTask : TaskViewModel

    private lateinit var adapter: RViewTaskAdapter

    private var listTask : MutableList<Task> = mutableListOf()

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentTaskBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        ToDoListApp.INSTANCE.appComponent.inject(this)

        viewModelTask = ViewModelProvider(this, taskViewModelFactory)
            .get(TaskViewModel::class.java)

        arguments?.let {
            param1 = it.getString("ListNum")
        }

        lifecycleScope.launch(Dispatchers.Main) {
            async(Dispatchers.IO) {
                viewModelTask.getAllTask(param1!!).forEach{task->
                    if(binding.calendarView.date == task.dateOfFutureExecution){
                        listTask.add(task)
                    }
                }
            }.await()
            adapter = RViewTaskAdapter(listTask, this@TaskFragment)
            val layoutManager = LinearLayoutManager(this@TaskFragment.requireContext())
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerView.layoutManager= layoutManager
            binding.recyclerView.adapter = adapter
        }

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            lifecycleScope.launch(Dispatchers.Main) {
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth, 0, 0, 0)
                val selectedDateInMillis = calendar.timeInMillis/1000

                async(Dispatchers.IO) {
                    listTask.clear()
                    viewModelTask.getAllTask(param1!!).forEach { task ->
                        Log.i("TAG", "${selectedDateInMillis} == ${task.dateOfFutureExecution}")
                        if (selectedDateInMillis == task.dateOfFutureExecution) {
                            listTask.add(task)
                        }
                    }
                }.await()
                adapter.addTask(listTask)
            }
        }

        binding.addNoteButton.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(id, AddTaskFragment.newInstance(param1!!)).commit()
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
           TaskFragment().apply {
                arguments = Bundle().apply {
                    putString("ListNum", id)
                }
            }
    }

    override fun onCompleteTaskListener(taskId: Int) {
        viewModelTask.updateStateCompletedTask(true, taskId)
    }

    override fun onDeleteTaskListener(taskId: Int) {
        viewModelTask.deleteTask(taskId)
        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.conteiner,
            TaskFragment.newInstance(param1!!)).commit()
    }

    override fun onUpdateTaskListener(taskId: Int) {
        requireActivity().supportFragmentManager.beginTransaction().replace(
            R.id.conteiner,
            UpdateTaskFragment.newInstance(param1!!, id)).commit()
    }
}