package com.example.utils.DateUtils

import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.utils.databinding.ActivityUtilsBinding
import java.time.LocalDateTime

@RequiresApi(VERSION_CODES.O)
class DateUtilsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUtilsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUtilsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            dateFormat1.text = DateUtils.formatDate(LocalDateTime.now().toString())
            dateFormat2.text = DateUtils.formatDateAndTime(LocalDateTime.now().toString())
            dateFormat3.text = DateUtils.getTimeFromhhmm("21:15").toString()
            dateFormat4.text = DateUtils.formatTime(LocalDateTime.now())
        }
    }
}