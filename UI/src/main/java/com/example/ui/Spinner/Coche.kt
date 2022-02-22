package com.example.ui.Spinner

data class Coche(
    val nombre: String,
    val marca: String,
    val color: String,
    val caballos: Int
) {

    override fun toString(): String = nombre
}