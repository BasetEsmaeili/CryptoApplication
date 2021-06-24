package com.baset.crypto.trader.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private val _isShowLoading = MutableLiveData(false)
    val isShowLoading: LiveData<Boolean> get() = _isShowLoading

    fun setLoadingState(isShowLoading: Boolean, emitOnUiThread: Boolean = true) {
        if (emitOnUiThread)
            _isShowLoading.value = isShowLoading
        else
            _isShowLoading.postValue(isShowLoading)
    }
}