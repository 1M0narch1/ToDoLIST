package curs.academy.tdl.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import curs.academy.domain.models.User
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.databinding.FragmentChoiceBinding
import curs.academy.tdl.ui.RViewChoiceAdapter
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChoiceFragment : Fragment() {

    private lateinit var binding : FragmentChoiceBinding
    private lateinit var adapter: RViewChoiceAdapter

    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    private var listUser : List<User> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentChoiceBinding.inflate(layoutInflater)
        ToDoListApp.INSTANCE.appComponent.inject(this)
        val viewModelUser = ViewModelProvider(this, userViewModelFactory)
            .get(UserViewModel::class.java)

        lifecycleScope.launch {
            setListUser(viewModelUser)
            adapter = RViewChoiceAdapter(requireContext())
            adapter.addUser(listUser)
            val layoutManager = LinearLayoutManager(this@ChoiceFragment.requireContext())
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
            binding.recyclerView.layoutManager = layoutManager
            binding.recyclerView.adapter = adapter
        }

        binding.goInButton.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(id, LogInFragment()).commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    private suspend fun setListUser(viewModel: UserViewModel){
        lifecycleScope.async(Dispatchers.IO) {
            listUser = viewModel.getAllUser()
        }.await()
    }
}