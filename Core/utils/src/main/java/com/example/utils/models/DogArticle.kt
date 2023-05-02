package com.example.utils.models

class DogArticle(var url:String = "",
                 var facts: MutableList<String> = mutableListOf(),
                 var isFavourite: Boolean=false)