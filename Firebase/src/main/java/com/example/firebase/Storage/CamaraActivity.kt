package com.example.firebase.Storage

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Gravity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.util.*

class CamaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

    }
    private fun setUpView() {
        binding.apply {
            tvInfo.text = "Actividad Guardar fotos en Firestore"
            tvInfo.gravity = Gravity.CENTER
            btCrash.text = "Take Photo"
            btCrash.setOnClickListener {
                takePhoto()
            }
        }
    }

    val getAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val bitmap = it?.data?.extras?.get("data") as Bitmap
        binding.ivFoto.setImageBitmap(bitmap)
        uploadPicture(bitmap)
    }


    private fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            if (takePictureIntent.resolveActivity(packageManager) != null){
                getAction.launch(takePictureIntent)
            }
        }
    }

    private fun uploadPicture(bitmap: Bitmap){
        val storageRef = FirebaseStorage.getInstance().reference
        val imagesRef = storageRef.child("images/${UUID.randomUUID()}.jpg")
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val uploadTask = imagesRef.putBytes(data)

        uploadTask.continueWithTask { task->
            if (!task.isSuccessful){
                task.exception?.let { exception->
                    throw exception
                }
            }
            imagesRef.downloadUrl
        }.addOnCompleteListener {task->
            if (task.isSuccessful){
                val downloadUrl = task.result.toString()
                FirebaseFirestore.getInstance().collection("ciudades").document("Madrid").update("fotoUrl", downloadUrl)
                Timber.d("FireStorage: ${downloadUrl}")
            }
        }
    }

}

