package com.example.revistasuteq.models

data class Revistas(
    val j_id: Int,
    val titulo: String,
    val descripcion: String,
    val issn: String,
    val imagen_url: String
)

data class Issues(
    val i_id: Int,
    val j_id: Int,
    val volumen: String,
    val numero: String,
    val year: String,
    val titulo: String,
    val cover: String = "",
    val date_published: String = ""
)

data class Volumens(
    val pub_id: Int,
    val i_id: Int,
    val titulo: String,
    val autores: String,
    val doi: String,
    val resumen: String
)