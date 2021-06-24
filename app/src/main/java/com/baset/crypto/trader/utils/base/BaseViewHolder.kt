package com.baset.crypto.trader.utils.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<M, B : ViewBinding>(binding: B) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun onBind(model: M)
}