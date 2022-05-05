package com.example.ui.RecyclerView.BasicPersonaRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.databinding.ItemPersonaBinding
import java.lang.ref.WeakReference

class PersonaListAdapter() :
    ListAdapter<Persona, PersonaListAdapter.PersonaViewHolder>(DifferCallback()) {

    private class DifferCallback : DiffUtil.ItemCallback<Persona>() {
        override fun areItemsTheSame(oldItem: Persona, newItem: Persona): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Persona, newItem: Persona): Boolean {
            return oldItem == newItem
        }
    }

    interface  PersonaAdapterListener{
        fun onDetalleClicked(persona: Persona)
        fun onEliminarClicked(persona: Persona)
    }

    private var personaAdapterListenerWeakReference: WeakReference<PersonaAdapterListener>? = null


    class PersonaViewHolder(val binding: ItemPersonaBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonaViewHolder {
        val binding = ItemPersonaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonaViewHolder, position: Int) {
        val persona = currentList[position]
        holder.binding.apply {
            tvNombre.text = persona.nombre
            tvAppellidos.text = persona.apellido
            tvDireccion.text = persona.direccion
            tvFechaNac.text = persona.fechaNaci

            //Ejemplo de como cargar una imagen con Glide
        /*    Glide
                .with(ivCardRowAvatarImage.context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.splash_background)
                .into(ivCardRowAvatarImage);*/

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

    fun setPersonasAdapterListener(@NonNull personaAdapterListener: PersonaAdapterListener) {
        this.personaAdapterListenerWeakReference = WeakReference(personaAdapterListener)
    }
}