package com.example.ui.RecyclerView

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.databinding.ActivityPersonaAdapterBinding

class PersonaAdapterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonaAdapterBinding
    private lateinit var adapterPersona: PersonaListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonaAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    }

    private val adapterListener = object : PersonaListAdapter.PersonaAdapterListener {
        override fun onDetalleClicked(persona: Persona) {
            Toast.makeText(this@PersonaAdapterActivity, "DETALLE: ${persona.nombre}",Toast.LENGTH_SHORT).show()
        }

        override fun onEliminarClicked(persona: Persona) {
            Toast.makeText(this@PersonaAdapterActivity, "ELIMINAR: ${persona.nombre}",Toast.LENGTH_SHORT).show()
        }
    }


    private fun setUpRecyclerView() {
        binding.rvPersona.apply {
            adapterPersona = PersonaListAdapter()
            adapter = adapterPersona
            adapterPersona.setPersonasAdapterListener(adapterListener)
            layoutManager = LinearLayoutManager(this@PersonaAdapterActivity)
            adapterPersona.submitList(listOf(
                Persona(1, "David  ","Alonso", "1-10-1998", "Calle principe de Asturias"),
                Persona(2, "Lucia","Zayas", "5-3-1998", "Calle Jose Bergamin"),
                Persona(3, "Miguel Angel","Alonso", "20-2-1996", "Calle principe de Asturias"),
                Persona(4, "Fernando","Alonso", "30-5-2003", "Calle principe de Asturias"),
            ).toMutableList())
        }
    }
}