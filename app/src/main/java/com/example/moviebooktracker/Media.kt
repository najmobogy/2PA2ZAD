package com.example.moviebooktracker

data class Media(
    val title: String, // Tytuł filmu/książki
    val creator: String, // Autor książki lub Reżyser filmu
    val year: Int, // Rok wydania
    val rating: Float // Ocena (0.0 - 5.0)
)
