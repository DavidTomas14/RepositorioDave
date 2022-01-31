package com.example.firebase.FireStore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.Ciudad
import com.example.firebase.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

        val db = FirebaseFirestore.getInstance()

       //Consultar Informacion (Ejemplo 1)
       /* val ciudades = db.collection("ciudades")
        ciudades.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if (document != null) {
                        val ciudad = document.toObject(Ciudad::class.java)
                        Timber.d("Firestore: DocumentSnapshot data: $ciudad")
                        val comunidad = document.getString("comunidad")
                        Timber.d("Firestore:-> ${comunidad} -> ${ciudad.nombre}")
                    } else {
                        Timber.d("Firestore: No such document")
                    }
                }
            }
            .addOnFailureListener { exception ->
                Timber.e("Firestore: get failed with ", exception)
            }*/

        //Consultar Informacion de manera Reactiva (Ejemplo 2)
        val cities = db.collection("ciudades")
        cities.
        addSnapshotListener { snapshot, error ->
            snapshot?.let {documents->
                val ciudades = documents.toObjects(Ciudad::class.java)
                for (ciudad in ciudades){
                    Timber.d("Firestore: ${ciudad.comunidad} -> ${ciudad.nombre}")
                }

            }
        }

        //Ingresar informacion (Ejemplo 3)
        db.collection("ciudades").document("Bilbao").set(Ciudad("Bilbao", "Pais Vasco"))
            .addOnSuccessListener {
                Timber.d("Firestore: Ciudad guardada correctamente")
            }
            .addOnFailureListener{exception->
                Timber.e("Firestore: get failed with ", exception)

            }
    }

    // Ejemplo Crashlytics
    private fun setUpView() {
        binding.apply {
            btCrash.text = "Test Crash"
            btCrash.setOnClickListener {
                throw RuntimeException("Test Crash")
            }
        }
    }
}
