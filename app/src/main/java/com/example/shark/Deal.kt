package com.example.shark
//Modelação de dados que serão mostrados
data class Deal (
    val title: String,
    val gameID: Int,
    val normalPrice: Double,
    val salePrice: Double,
    val savings: Double,
    val thumb: String
)