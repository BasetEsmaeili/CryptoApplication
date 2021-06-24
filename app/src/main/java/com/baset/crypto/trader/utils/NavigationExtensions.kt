package com.baset.crypto.trader.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController

fun <T> Fragment.setFragmentResult(key: String, value: T) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(
        key,
        value
    )
}

fun <T>Fragment.getFragmentResult(@IdRes id: Int, key: String, onResult: (result: T) -> Unit) {
    try {
        val navBackStackEntry = findNavController().getBackStackEntry(id)
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME && navBackStackEntry.savedStateHandle.contains(key)) {
                val result = navBackStackEntry.savedStateHandle.getLiveData<T>(key)
                result.observe(viewLifecycleOwner){
                    onResult.invoke(it)
                }
                navBackStackEntry.savedStateHandle.remove<T>(key)
            }
        }
        navBackStackEntry.lifecycle.addObserver(observer)
        viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_DESTROY) {
                navBackStackEntry.lifecycle.removeObserver(observer)
            }
        })
    } catch (e: Exception) {
        verboseLog("Navigation Extensions", e)
    }
}