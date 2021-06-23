package com.baset.crypto.trader.ui.cryptolist

import android.content.Context
import androidx.fragment.app.viewModels
import com.baset.crypto.trader.R
import com.baset.crypto.trader.databinding.FragmentCryptoListBinding
import com.baset.crypto.trader.di.app.findAppComponent
import com.baset.crypto.trader.di.cryptolist.DaggerCryptoListComponent
import com.baset.crypto.trader.ui.base.BaseFragment
import com.baset.crypto.trader.utils.factory.ViewModelFactory
import javax.inject.Inject

class CryptoListFragment :
    BaseFragment<FragmentCryptoListBinding, CryptoListViewModel>(R.layout.fragment_crypto_list) {
    @Inject
    override lateinit var viewModelFactory: ViewModelFactory
    override val viewModel: CryptoListViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCryptoListComponent.builder().appComponent(findAppComponent()).build().inject(this)
    }
}