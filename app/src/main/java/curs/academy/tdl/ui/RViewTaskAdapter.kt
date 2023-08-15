package curs.academy.tdl.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import curs.academy.domain.models.Task
import curs.academy.tdl.R
import curs.academy.tdl.databinding.TaskItemBinding
import curs.academy.tdl.viewmodel.TaskViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class RViewTaskAdapter(private var taskList : List<Task>, private val taskButtons: TaskButtons
                       ) : RecyclerView.Adapter<RViewTaskAdapter.RViewTaskHolder>() {


    private val job = SupervisorJob()
    private val taskScope = CoroutineScope(job+ Dispatchers.IO)

    class RViewTaskHolder(item: View, private val onUpdateTask: OnUpdateTask,
                          private val onDeleteTask: OnDeleteTask,
                          private val onCompleteTask: OnCompleteTask
                          ) : RecyclerView.ViewHolder(item) {

        private val job = SupervisorJob()
        private val taskScope = CoroutineScope(job+ Dispatchers.IO)

        private val binding = TaskItemBinding.bind(item)
        fun bind(t : Task){
            binding.apply {
                t.apply {
                    textNote.text = text
                    timeNoteText.text = SimpleDateFormat("yyyy-MM-dd HH:mm").format(dateOfCreation).toString()
                    if(t.taskCompleted){
                        completed.setBackgroundResource(R.drawable.baseline_check_24)
                    }
                    completed.setOnClickListener{
                        completed.setBackgroundResource(R.drawable.baseline_check_24)
                        onCompleteTask.onCompleteTaskListener(t.id)
                    }
                    deleteButton.setOnClickListener{
                        taskScope.launch(Dispatchers.IO) {
                            onDeleteTask.onDeleteTaskListener(t.id)
                        }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewTaskAdapter.RViewTaskHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return RViewTaskHolder(view, taskButtons, taskButtons, taskButtons)
    }

    override fun onBindViewHolder(holder: RViewTaskHolder, position: Int) {
        holder.bind(taskList[position])
    }


    override fun getItemCount(): Int {
        return taskList.size
    }

    fun addTask(list:List<Task>){
        taskList = list
        notifyDataSetChanged()
    }

    interface OnCompleteTask {
        fun onCompleteTaskListener(taskId: Int)
    }

    interface OnDeleteTask {
        fun onDeleteTaskListener(taskId: Int)
    }

    interface OnUpdateTask {
        fun onUpdateTaskListener(taskId: Int)
    }

    interface TaskButtons : OnCompleteTask, OnDeleteTask, OnUpdateTask
}
