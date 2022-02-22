package com.example.rxjava.app

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjava.R
import com.example.rxjava.databinding.ActivityMainBinding
import com.example.rxjava.model.User
import com.example.rxjava.util.ResourceState
import com.example.rxjava.util.ResourceState.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initState()
        initViews()

    }

    private fun initState() {
        userViewModel.obtenerUsers().observe(this) {
            handleState(it)
        }
        if (userViewModel.obtenerUsers().value == null) {
            userViewModel.fetchUsersObservable()
        }

        userViewModel.obtenerUser().observe(this) {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

        userViewModel.obtenerUserCommand().observe(this) {
            it.getContentIfNotHandled()?.let {
                handleUserCommandState(it)
            }
        }
    }

    private fun handleUserCommandState(userCommand: UserCommand) {
        when (userCommand) {
            is UserCommand.ErrorDeleteUserCommand -> {
                Toast.makeText(this, getString(R.string.error_delete), Toast.LENGTH_SHORT).show()
            }
            is UserCommand.CompletedDeleteUserCommand -> {
                Toast.makeText(this, getString(R.string.confirmacion_delete), Toast.LENGTH_SHORT).show()
            }
            is UserCommand.ErrorCreadoUserCommand->{
                Toast.makeText(this, getString(R.string.error_create), Toast.LENGTH_SHORT).show()
            }
            is UserCommand.CompletedCreadoUserCommand->{
                Toast.makeText(this, getString(R.string.confirmacion_create), Toast.LENGTH_SHORT).show()
            }
        }

    }


    private fun handleState(resourceState: ResourceState<List<User>>?) {
        when (resourceState) {
            is Loading -> {
                setLoadingScreen()
            }
            is Error -> {
                Toast.makeText(this, resourceState.errorBundle, Toast.LENGTH_SHORT).show()
            }
            is Success -> {
                setSuccessScreen(resourceState.data)
            }
        }
    }

    private fun setLoadingScreen() {
        binding.pbLoading.visibility = VISIBLE
        binding.tvUsers.visibility = GONE
    }

    private fun setSuccessScreen(users: List<User>) {
        binding.pbLoading.visibility = GONE
        binding.tvUsers.visibility = VISIBLE
        binding.tvUsers.text = users.toString()
    }

    private fun initViews() {
        binding.btCambiarNombre.setOnClickListener {
            userViewModel.setUserName()
        }

        binding.btDeleteUser.setOnClickListener {
            userViewModel.deleteUserById()
        }

        binding.btCrearUser.setOnClickListener {
            userViewModel.crearUser()
        }
    }
}