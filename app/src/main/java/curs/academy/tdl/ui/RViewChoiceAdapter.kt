package curs.academy.tdl.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import curs.academy.domain.models.User
import curs.academy.tdl.R
import curs.academy.tdl.activity.MainActivity
import curs.academy.tdl.databinding.ChoiceItemBinding

class RViewChoiceAdapter(private val context: Context) : RecyclerView.Adapter<RViewChoiceAdapter.RViewChoiceHolder>() {

    private lateinit var userList : List<User>

    class RViewChoiceHolder(private val context: Context, item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ChoiceItemBinding.bind(item)
        fun bind(u : User){
            binding.apply {
                u.apply {
                    binding.loginAccountText.text = u.login
                    binding.goInButton.setOnClickListener {
                        val intent = Intent(itemView.context, MainActivity::class.java)
                        intent.putExtra("userId", u.userId)
                        context.startActivity(intent)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RViewChoiceHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.choice_item, parent, false)
        return RViewChoiceHolder(context,view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RViewChoiceHolder, position: Int) {
        holder.bind(userList[position])
    }

    fun addUser(user: List<User>){
       userList = user
        notifyDataSetChanged()
    }
}