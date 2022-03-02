package com.example.ui.RecyclerView.DifferentTypesAndHeaderRv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ui.databinding.ActivityMultipleDataAdapterBinding
import com.example.ui.databinding.ActivityPersonaAdapterBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMultipleDataAdapterBinding
    private lateinit var animalFruitAdapter: AnimalFruitAdapter

    private val adapterListener = object : AnimalFruitAdapter.FruitAdapterListener{
        override fun showToast(itemName: String) {
            Toast.makeText(this@MainActivity, itemName, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMultipleDataAdapterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        binding.rvMultipleData.apply {
            animalFruitAdapter = AnimalFruitAdapter()
            animalFruitAdapter.setAnimalAdapterListener(adapterListener)
            adapter = animalFruitAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            animalFruitAdapter.setItemList(listOf(
                Fruit("Sandia", "Red", "Dulce"),
                Fruit("Platano", "Amarillo", "SemiDulce"),
                Animal("Perro", "Carnívoro", "Víviparo"),
                Fruit("Platano", "Amarillo", "SemiDulce"),
                Animal("Perro", "Carnívoro", "Víviparo"),
                Animal("Perro", "Carnívoro", "Víviparo"),
                Fruit("Sandia", "Red", "Dulce"),
                ).toMutableList())
        }
    }
}