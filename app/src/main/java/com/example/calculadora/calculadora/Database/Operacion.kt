package com.example.calculadora.calculadora.Database

/**
  modelo que representa las operaciones artitmeticas realiza por la calculadora
 se guarda en el SQLite con un ID incremental
 */
data class Operacion(
    val id: Int=0, // id unico generado por sqlite
    val descripcion: String, // texto la operacion (ej "5+5=10")
    val fecha: String //fecha de la operacion

)