package com.baset.crypto.trader.di.app

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import com.baset.crypto.trader.CryptoApplication

fun Context.findAppComponent(): AppComponent = (applicationContext as? CryptoApplication)?.provideAppComponent() ?: throw IllegalArgumentException("Application Class Must Implement AppComponent Provider")

fun View.findAppComponent(): AppComponent = context.findAppComponent()

fun Fragment.findAppComponent(): AppComponent = requireContext().findAppComponent()