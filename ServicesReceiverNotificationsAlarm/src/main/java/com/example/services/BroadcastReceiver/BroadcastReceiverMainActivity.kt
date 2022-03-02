package com.example.services.BroadcastReceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.services.databinding.BroadcastReceiverActivityMainBinding

class BroadcastReceiverMainActivity : AppCompatActivity() {

    private lateinit var binding: BroadcastReceiverActivityMainBinding
    lateinit var receiver: AirplaneModeChangedReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BroadcastReceiverActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        receiver = AirplaneModeChangedReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}