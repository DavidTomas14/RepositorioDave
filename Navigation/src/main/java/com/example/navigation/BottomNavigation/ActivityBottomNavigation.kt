package com.example.navigation.BottomNavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navigation.databinding.ActivityBottomNavigationBinding
import com.example.navigation.databinding.ActivityNavigatorActivitiesBinding

class ActivityBottomNavigation : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {

    }
}