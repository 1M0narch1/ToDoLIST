package curs.academy.tdl.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import curs.academy.domain.models.User
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.databinding.ActivityAuthBinding
import curs.academy.tdl.fragment.auth.ChoiceFragment
import curs.academy.tdl.fragment.auth.HelloFragment
import curs.academy.tdl.fragment.auth.LogInFragment
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAuthBinding

    @Inject
    lateinit var userViewModelFactory: UserViewModelFactory

    private var listUser : List<User> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch(Dispatchers.IO) {
   //         ToDoListApp.INSTANCE.database.getUserDao().deleteAll()
        }
        ToDoListApp.INSTANCE.appComponent.inject(this)

        val viewModelUser = ViewModelProvider(this, userViewModelFactory)
            .get(UserViewModel::class.java)

        lifecycleScope.launch(Dispatchers.IO) {
            setListUser(viewModelUser)

            launch(Dispatchers.Main) {
                if (listUser.isNotEmpty()) {
                    supportFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .replace(binding.conteiner.id, ChoiceFragment()).commit()
                } else {
                    supportFragmentManager.beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .replace(binding.conteiner.id, HelloFragment()).commit()
                }
            }
        }
    }

    private suspend fun setListUser(viewModel: UserViewModel){
        lifecycleScope.async(Dispatchers.IO) {
            listUser = viewModel.getAllUser()
        }.await()
    }
}