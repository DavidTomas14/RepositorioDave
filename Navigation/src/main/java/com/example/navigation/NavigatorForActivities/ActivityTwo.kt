package com.example.navigation.NavigatorForActivities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.navigation.databinding.ActivityNavigatorActivitiesBinding

class ActivityTwo : AppCompatActivity() {

    private lateinit var binding: ActivityNavigatorActivitiesBinding


    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, ActivityOne::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigatorActivitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {
        binding.btnNavegar.setOnClickListener {
            Navigator.navigateToActivityOne(this)
        }

        binding.tvActivity.text = "Activity Two"
    }
}