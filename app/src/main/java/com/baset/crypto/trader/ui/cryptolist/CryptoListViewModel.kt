package com.baset.crypto.trader.ui.cryptolist

import androidx.lifecycle.viewModelScope
import com.baset.crypto.domain.entity.ErrorEntity
import com.baset.crypto.domain.entity.Status
import com.baset.crypto.domain.entity.params.*
import com.baset.crypto.domain.usecase.ClearCryptocurrenciesUseCase
import com.baset.crypto.domain.usecase.GetLocalCryptocurrenciesUseCase
import com.baset.crypto.domain.usecase.GetRemoteCryptocurrenciesUseCase
import com.baset.crypto.trader.mapper.CryptocurrencyListToCryptocurrencyEntityMapper
import com.baset.crypto.trader.ui.base.BaseViewModel
import com.baset.crypto.trader.utils.threading.DispatcherProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CryptoListViewModel @Inject constructor(
    private val getRemoteCryptocurrenciesUseCase: GetRemoteCryptocurrenciesUseCase,
    getLocalCryptocurrenciesUseCase: GetLocalCryptocurrenciesUseCase,
    private val cryptocurrencyMapper: CryptocurrencyListToCryptocurrencyEntityMapper,
    private val clearCryptocurrenciesUseCase: ClearCryptocurrenciesUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel() {
    val cryptocurrencies = getLocalCryptocurrenciesUseCase(dispatcher = dispatcher.io())
        .onStart { setLoadingState(isShowLoading = true, emitOnUiThread = true) }
        .map { cryptocurrencyMapper.mapToEntity(it) }.distinctUntilChanged()
    val errorEvent = MutableStateFlow<ErrorEntity?>(null)
    val isLastPage = MutableStateFlow(false)
    val sortType = MutableStateFlow(CryptocurrencySortType.PRICE)
    val sortDirection = MutableStateFlow(SortDirection.DESCENDING)
    val cryptoType = MutableStateFlow(CryptocurrencyFilterType.ALL)
    val tagType = MutableStateFlow(TagFilterType.ALL)
    private var page = 0

    init {
        resetContent()
    }

    fun getCryptocurrencies() {
        getCryptocurrencies(++page)
    }

    private fun getCryptocurrencies(page: Int) =
        viewModelScope.launch(dispatcher.io()) {
            setLoadingState(isShowLoading = true, emitOnUiThread = false)
            val response = getRemoteCryptocurrenciesUseCase(
                GetRemoteCryptocurrenciesParams(
                    page = page,
                    sortBy = sortType.value,
                    sortDirection = sortDirection.value,
                    cryptocurrencyType = cryptoType.value,
                    tagType = tagType.value
                )
            )
            if (response.status == Status.SUCCESS)
                setIsLastPage(response.data.isNullOrEmpty())
            else {
                setErrorEvent(response.errorType)
            }
            setLoadingState(isShowLoading = false, emitOnUiThread = false)
        }

    private suspend fun setErrorEvent(errorType: ErrorEntity?) {
        errorEvent.emit(errorType)
    }

    private suspend fun setIsLastPage(isLast: Boolean) {
        isLastPage.emit(isLast)
    }

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

    fun resetContent() = viewModelScope.launch(dispatcher.io()) {
        withContext(dispatcher.default()) {
            clearCryptocurrenciesUseCase()
        }
        page = 0
    }
}