package com.example.ui.doglist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.utils.models.DogArticle

class DogListDiffUtill(val oldList: MutableList<DogArticle>, val newList: MutableList<DogArticle>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldListSize
    }

    override fun getNewListSize(): Int {
        return  newListSize
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val olditem = oldList[oldItemPosition]
        val newitem = newList[newItemPosition]
       return (olditem::class == newitem::class)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }

}