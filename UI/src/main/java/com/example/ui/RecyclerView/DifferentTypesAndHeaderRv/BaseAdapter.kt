package com.example.ui.RecyclerView.DifferentTypesAndHeaderRv

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    abstract fun setItemList(list: List<Any>)

}