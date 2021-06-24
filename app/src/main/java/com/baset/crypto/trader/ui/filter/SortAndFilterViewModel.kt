package com.baset.crypto.trader.ui.filter

import androidx.lifecycle.viewModelScope
import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType
import com.baset.crypto.trader.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SortAndFilterViewModel @Inject constructor() : BaseViewModel() {
    val sortType = MutableStateFlow(CryptocurrencySortType.PRICE)
    val sortDirection = MutableStateFlow(SortDirection.DESCENDING)
    val cryptoType = MutableStateFlow(CryptocurrencyFilterType.ALL)
    val tagType = MutableStateFlow(TagFilterType.ALL)
    fun setSortType(type: CryptocurrencySortType) = viewModelScope.launch {
        sortType.emit(type)
    }

    fun setSortDirection(direction: SortDirection) = viewModelScope.launch {
        sortDirection.emit(direction)
    }

    fun setCryptoType(type: CryptocurrencyFilterType) = viewModelScope.launch {
        cryptoType.emit(type)
    }

    fun setTagType(type: TagFilterType) = viewModelScope.launch {
        tagType.emit(type)
    }
}