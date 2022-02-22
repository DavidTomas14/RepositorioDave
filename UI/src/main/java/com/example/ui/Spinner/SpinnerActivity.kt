package com.example.ui.Spinner

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.databinding.ActivityPersonaAdapterBinding
import com.example.ui.databinding.ActivitySpinnerBinding
import org.koin.android.ext.android.inject

class SpinnerActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpinnerBinding
    private val spinnerAdapter: ArrayAdapter<Coche> by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpSpinner()
    }

    private fun setUpSpinner() {

        binding.spinner.apply {
            adapter = spinnerAdapter

            spinnerAdapter.addAll(
                Coche("Puegeot 208", "Peugeot", "Azul", 100),
                Coche("Ford Focus", "Ford", "Blanco", 120),
                Coche("Panamera", "Porsche", "Negro", 200),
            )

            spinnerAdapter.notifyDataSetChanged()

            onItemSelectedListener = object: OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    //Como en la data class Coche sobreescribimos el toString se muestra unicamente el nombre en el Spinner
                    binding.tvSpinnerSeleccion.text = spinnerAdapter.getItem(position)?.nombre ?: ""

                    //Muy útil para recuperar toda la info del coche Seleccionado
                    val cocheSeleccionado = parent?.getItemAtPosition(position) as Coche

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }

        }
    }
}