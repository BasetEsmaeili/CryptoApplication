package com.baset.crypto.trader.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.baset.crypto.trader.R
import com.baset.crypto.trader.databinding.ItemviewCryptoBinding
import com.baset.crypto.trader.entity.Cryptocurrency
import com.baset.crypto.trader.utils.base.BaseViewHolder
import com.baset.crypto.trader.utils.factory.CryptoItemFactory

class CryptoListAdapter(private val cryptoItemFactory: CryptoItemFactory) :
    ListAdapter<Cryptocurrency, CryptoListAdapter.CryptoListViewHolder>(CryptoDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CryptoListViewHolder(
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.itemview_crypto,
                parent,
                false
            ), cryptoItemFactory
        )
    }

    override fun onBindViewHolder(holder: CryptoListViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class CryptoListViewHolder(
        private val binding: ItemviewCryptoBinding,
        private val factory: CryptoItemFactory
    ) :
        BaseViewHolder<Cryptocurrency, ItemviewCryptoBinding>(binding) {
        override fun onBind(model: Cryptocurrency) {
            binding.model = model
            binding.factory = factory
        }
    }
}

object CryptoDiffUtil : DiffUtil.ItemCallback<Cryptocurrency>() {

    override fun areItemsTheSame(
        oldItem: Cryptocurrency,
        newItem: Cryptocurrency
    ): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(
        oldItem: Cryptocurrency,
        newItem: Cryptocurrency
    ): Boolean {
        return newItem.equalsContent(oldItem)
    }

    override fun getChangePayload(oldItem: Cryptocurrency, newItem: Cryptocurrency): Any {
        return false
    }
}