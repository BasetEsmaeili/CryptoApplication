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
import kotlinx.coroutines.launch
import javax.inject.Inject

class CryptoListViewModel @Inject constructor(
    private val getRemoteCryptocurrenciesUseCase: GetRemoteCryptocurrenciesUseCase,
    getLocalCryptocurrenciesUseCase: GetLocalCryptocurrenciesUseCase,
    private val cryptocurrencyMapper: CryptocurrencyListToCryptocurrencyEntityMapper,
    private val clearCryptocurrenciesUseCase: ClearCryptocurrenciesUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel() {
    val cryptocurrencies = getLocalCryptocurrenciesUseCase(dispatcher = dispatcher.io())
        .map { cryptocurrencyMapper.mapToEntity(it) }.distinctUntilChanged()
    val errorEvent = MutableStateFlow<ErrorEntity?>(null)
    val isLastPage = MutableStateFlow(false)
    val sortType = MutableStateFlow(CryptocurrencySortType.PRICE)
    val sortDirection = MutableStateFlow(SortDirection.DESCENDING)
    val filterType = MutableStateFlow(CryptocurrencyFilterType.ALL)
    val tagType = MutableStateFlow(TagFilterType.ALL)
    private var page = 1

    init {
        getCryptocurrencies(page)
    }

    fun getCryptocurrencies() {
        getCryptocurrencies(++page)
    }

    private fun getCryptocurrencies(page: Int) = viewModelScope.launch(dispatcher.io()) {
        setLoadingState(isShowLoading = true, emitOnUiThread = false)
        val response = getRemoteCryptocurrenciesUseCase(
            GetRemoteCryptocurrenciesParams(
                page = page,
                sortBy = sortType.value,
                sortDirection = sortDirection.value,
                cryptocurrencyType = filterType.value,
                tagType = tagType.value
            )
        )
        if (response.status == Status.SUCCESS)
            setIsLastPage(response.data.isNullOrEmpty())
        else
            setErrorEvent(response.errorType)
        setLoadingState(isShowLoading = false, emitOnUiThread = false)
    }

    private suspend fun setErrorEvent(errorType: ErrorEntity?) {
        errorEvent.emit(errorType)
    }

    private suspend fun setIsLastPage(isLast: Boolean) {
        isLastPage.emit(isLast)
    }
}