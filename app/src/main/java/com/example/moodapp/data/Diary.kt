package com.example.moodapp.data

data class Diary(
        val title: String,
        val date: String,
        val content: String,
        val imageUrl: String = ""
)