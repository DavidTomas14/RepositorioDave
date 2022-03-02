package com.example.ui.RecyclerView.DifferentTypesAndHeaderRv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.ui.databinding.ItemAnimalBinding
import com.example.ui.databinding.ItemFruitBinding
import com.example.ui.databinding.TableHeaderBinding
import java.lang.ref.WeakReference

class AnimalFruitAdapter() :
    BaseAdapter() {

    companion object {

        private const val TYPE_HEADER = 0
        private const val TYPE_FRUIT = 1
        private const val TYPE_ANIMAL = 2
    }

    interface  FruitAdapterListener{
        fun showToast(itemName: String)
    }

    private var itemsList: List<Any> = ArrayList()
    private var animalFruitAdapterListenerWeakReference: WeakReference<FruitAdapterListener>? = null


    class AnimalViewHolder(val binding: ItemAnimalBinding) : RecyclerView.ViewHolder(binding.root)
    class FruitViewHolder(val binding: ItemFruitBinding) : RecyclerView.ViewHolder(binding.root)
    class HeaderViewHolder(val binding: TableHeaderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = itemsList.size

    override fun setItemList(list: List<Any>) {
        itemsList = list
        notifyDataSetChanged()
    }



    override fun getItemViewType(position: Int): Int {
        if (position==0) return TYPE_HEADER
        return when(itemsList[position]){
            is Fruit -> TYPE_FRUIT
            is Animal -> TYPE_ANIMAL
            else -> {
                throw IllegalStateException("Type not supported")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_HEADER ->{
                val binding = TableHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                HeaderViewHolder(binding)
            }
            TYPE_ANIMAL ->{
                val binding = ItemAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AnimalViewHolder(binding)
            }
            TYPE_FRUIT ->{
                val binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                FruitViewHolder(binding)
            }
            else -> {
                throw IllegalStateException("Type not supported")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AnimalViewHolder){
            bindAnimal(holder, position)
        }else if (holder is FruitViewHolder){
            bindFruit(holder, position)
        }
    }


    private fun bindFruit(holder: FruitViewHolder, position: Int) {
        val fruit = itemsList[position] as Fruit
        holder.binding.apply {
            llFruta.setOnClickListener {
                animalFruitAdapterListenerWeakReference?.get()?.showToast(fruit.name)
            }
            tvName.text = fruit.name
            tvColor.text = fruit.color
            tvType.text = fruit.type
        }
    }

    private fun bindAnimal(holder: AnimalViewHolder, position: Int) {
        val animal = itemsList[position] as Animal
        holder.binding.apply {
            holder.binding.apply {
                llAnimal.setOnClickListener {
                    animalFruitAdapterListenerWeakReference?.get()?.showToast(animal.name)
                }
                tvName.text = animal.name
                tvAlimentacion.text = animal.alimentacion
                tvReproduccion.text = animal.reproduccon
            }
        }
    }



    fun setAnimalAdapterListener(@NonNull animalFruitAdapterListener: FruitAdapterListener) {
        this.animalFruitAdapterListenerWeakReference = WeakReference(animalFruitAdapterListener)
    }
}