package com.baset.crypto.trader.ui.filter

import androidx.lifecycle.viewModelScope
import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType
import com.baset.crypto.trader.ui.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SortAndFilterViewModel @Inject constructor() : BaseViewModel() {
    private val _sortType = MutableStateFlow(CryptocurrencySortType.PRICE)
    val sortType: StateFlow<CryptocurrencySortType> by this::_sortType
    private val _sortDirection = MutableStateFlow(SortDirection.DESCENDING)
    val sortDirection: StateFlow<SortDirection> by this::_sortDirection
    private val _cryptoType = MutableStateFlow(CryptocurrencyFilterType.ALL)
    val cryptoType: StateFlow<CryptocurrencyFilterType> by this::_cryptoType
    private val _tagType = MutableStateFlow(TagFilterType.ALL)
    val tagType: StateFlow<TagFilterType> by this::_tagType
    fun setSortType(type: CryptocurrencySortType) = viewModelScope.launch {
        _sortType.emit(type)
    }

    fun setSortDirection(direction: SortDirection) = viewModelScope.launch {
        _sortDirection.emit(direction)
    }

    fun setCryptoType(type: CryptocurrencyFilterType) = viewModelScope.launch {
        _cryptoType.emit(type)
    }

    fun setTagType(type: TagFilterType) = viewModelScope.launch {
        _tagType.emit(type)
    }
}