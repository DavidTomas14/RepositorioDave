package com.example.ui.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.databinding.ItemPersonaBinding
import java.lang.ref.WeakReference

class PersonaListAdapter() :
    ListAdapter<Persona, PersonaListAdapter.PersonaViewHolder>(DifferCallback) {

    companion object {
        object DifferCallback : DiffUtil.ItemCallback<Persona>() {
            override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface  PersonaAdapterListener{
        fun onDetalleClicked(persona: Persona)
        fun onEliminarClicked(persona: Persona)
    }

    private var personasList: List<Persona> = ArrayList()
    private var personaAdapterListenerWeakReference: WeakReference<PersonaAdapterListener>? = null


    class PersonaViewHolder(val binding: ItemPersonaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = personasList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding = ItemPersonaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = personasList[position]
        holder.binding.apply {
            tvNombre.text = persona.nombre
            tvAppellidos.text = persona.apellido
            tvDireccion.text = persona.direccion
            tvFechaNac.text = persona.fechaNaci

            btnDetalle.setOnClickListener {
                this@PersonaListAdapter.personaAdapterListenerWeakReference
                    ?.get()?.onDetalleClicked(persona)
            }
            btnEliminar.setOnClickListener {
                this@PersonaListAdapter.personaAdapterListenerWeakReference
                    ?.get()?.onEliminarClicked(persona)
            }
        }
    }

     override fun submitList(list: MutableList<Persona>?) {
        if (personasList.isEmpty()) {
            personasList = list!!
        }
    }

    fun setOfficesAdapterListener(@NonNull personaAdapterListener: PersonaAdapterListener) {
        this.personaAdapterListenerWeakReference = WeakReference(personaAdapterListener)
    }
}