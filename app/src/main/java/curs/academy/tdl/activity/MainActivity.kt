package curs.academy.tdl.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import curs.academy.domain.models.User
import curs.academy.tdl.R
import curs.academy.tdl.ToDoListApp
import curs.academy.tdl.databinding.ActivityMainBinding
import curs.academy.tdl.fragment.main.NoteFragment
import curs.academy.tdl.fragment.main.ProfileFragment
import curs.academy.tdl.fragment.main.TaskFragment
import curs.academy.tdl.viewmodel.UserViewModel
import curs.academy.tdl.viewmodel.UserViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intetnt = intent
        val listNum = intetnt.getStringExtra("userId")
        supportFragmentManager.beginTransaction().replace(R.id.conteiner,
            NoteFragment.newInstance(listNum!!)).commit()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.note -> supportFragmentManager.beginTransaction().replace(R.id.conteiner,
                    NoteFragment.newInstance(listNum)).commit()
                R.id.task -> supportFragmentManager.beginTransaction().replace(R.id.conteiner,
                    TaskFragment.newInstance(listNum)).commit()
                R.id.profile -> supportFragmentManager.beginTransaction().replace(R.id.conteiner,
                    ProfileFragment.newInstance(listNum)).commit()
                else->{
                }
            }
            true
        }


    }

}