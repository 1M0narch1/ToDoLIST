package curs.academy.tdl.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.activity.MainActivity
import curs.academy.tdl.databinding.FragmentLogInBinding
import curs.academy.tdl.viewmodel.AuthViewModel
import curs.academy.tdl.viewmodel.AuthViewModelFactory
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInFragment : Fragment() {

    private lateinit var binding : FragmentLogInBinding
    @Inject
    lateinit var authViewModelFactory: AuthViewModelFactory
    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentLogInBinding.inflate(layoutInflater)
        ToDoListApp.INSTANCE.appComponent.inject(this)

        val viewModelAuth = ViewModelProvider(this, authViewModelFactory)
            .get(AuthViewModel::class.java)
        val viewModelUser = ViewModelProvider(this, userViewModelFactory)
            .get(UserViewModel::class.java)

        binding.button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                val authStatus = viewModelAuth.loginUser(binding.loginEditText.text.toString(),
                    binding.passwordEditText.text.toString())
                if(authStatus.status) {
                    lifecycleScope.launch(Dispatchers.IO) {

                        viewModelUser.insertUser(
                            binding.loginEditText.text.toString(),
                            binding.passwordEditText.text.toString(),
                            authStatus.userId
                        )

                        launch(Dispatchers.Main) {
                            val intent = Intent(requireContext(), MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            intent.putExtra("userId", authStatus.userId)
                            startActivity(intent)
                            requireActivity().finish()
                        }
                    }
                    }
                    else{
                    launch(Dispatchers.Main) {
                        binding.validationMesegeTextView.text = authStatus.error
                    }
                }
            }
        }

        binding.textButton.setOnClickListener{
            requireActivity().supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(id, RegistrationFragment()).commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }
}