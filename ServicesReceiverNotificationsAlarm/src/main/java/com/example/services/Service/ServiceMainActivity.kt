package com.example.services.Service

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.services.databinding.SeviceActivityMainBinding

class ServiceMainActivity : AppCompatActivity() {

    private lateinit var binding: SeviceActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SeviceActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                binding.tvServiceInfo.text = "Service Running"
            }
        }


        binding.btnStop.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                binding.tvServiceInfo.text = "Service Stopped"
            }
        }

        binding.btnSendData.setOnClickListener {
            Intent(this, MyService::class.java).also {
                val dataString = binding.tietDataString.text.toString()
                it.putExtra("EXTRA_DATA", dataString)
                startService(it)
            }
        }

    }
}