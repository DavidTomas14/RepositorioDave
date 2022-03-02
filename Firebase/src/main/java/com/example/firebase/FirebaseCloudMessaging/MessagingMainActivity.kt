package com.example.firebase.FirebaseCloudMessaging

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.Ciudad
import com.example.firebase.R
import com.example.firebase.databinding.ActivityMainBinding
import com.example.firebase.databinding.MessagingMainActivityBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber

class MessagingMainActivity : AppCompatActivity() {

    private lateinit var binding: MessagingMainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MessagingMainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notification()
    }

    private fun notification() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.d("Token: Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            // Log and toast
            val msg = getString(R.string.msg_token_fmt)
            Timber.d("Token: $token")
            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
        // Temas o Topics
        FirebaseMessaging.getInstance().subscribeToTopic("repositorio")

        // Recuperar Información
        /*val url = intent.getStringExtra("url")
        url?.let {
            Timber.d("Ha llegado informacion en una push: $url")
        }*/
    }
}
