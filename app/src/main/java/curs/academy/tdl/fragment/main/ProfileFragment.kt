package curs.academy.tdl.fragment.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.activity.AuthActivity
import curs.academy.tdl.activity.MainActivity
import curs.academy.tdl.databinding.FragmentProfileBinding
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        ToDoListApp.INSTANCE.appComponent.inject(this)

        val viewModelUser = ViewModelProvider(this, userViewModelFactory)
            .get(UserViewModel::class.java)
        arguments?.let {
            param1 = it.getString("ListNum")
        }

        binding.leaveButton.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val user = viewModelUser.getUserById(param1!!)
                viewModelUser.deleteUser(user.id)
            }
            val intent = Intent(requireContext(), AuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            requireActivity().finish()
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
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString("ListNum", id)
                }
            }
    }
}