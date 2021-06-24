package com.baset.crypto.trader.ui.filter

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.baset.crypto.domain.entity.params.CryptocurrencyFilterType
import com.baset.crypto.domain.entity.params.CryptocurrencySortType
import com.baset.crypto.domain.entity.params.SortDirection
import com.baset.crypto.domain.entity.params.TagFilterType
import com.baset.crypto.trader.R
import com.baset.crypto.trader.databinding.DialogSortAndFilterBinding
import com.baset.crypto.trader.di.app.findAppComponent
import com.baset.crypto.trader.di.filter.DaggerSortAndFilterComponent
import com.baset.crypto.trader.entity.SortAndFilterParams
import com.baset.crypto.trader.utils.Constants
import com.baset.crypto.trader.utils.base.BaseBottomSheet
import com.baset.crypto.trader.utils.factory.ViewModelFactory
import com.baset.crypto.trader.utils.setFragmentResult
import javax.inject.Inject

class SortAndFilterBottomSheet :
    BaseBottomSheet<DialogSortAndFilterBinding, SortAndFilterViewModel>(R.layout.dialog_sort_and_filter) {

    private val args: SortAndFilterBottomSheetArgs by navArgs()

    @Inject
    override lateinit var viewModelFactory: ViewModelFactory
    override val viewModel: SortAndFilterViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerSortAndFilterComponent.builder().appComponent(findAppComponent()).build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupArguments()
    }

    private fun setupArguments() {
        binding.chGroupSortBy.check(getSelectedSortByItem())
        binding.chGroupSortDirection.check(getSelectedSortDirectionItem())
        binding.chGroupCryptoType.check(getSelectedCryptoTypeItem())
        binding.chGroupTagType.check(getSelectedTagTypeItem())
    }

    private fun getSelectedSortByItem(): Int {
        return when (args.params.sortType) {
            CryptocurrencySortType.MARKET_CAP -> R.id.chip_market_cap
            CryptocurrencySortType.NAME -> R.id.chip_name
            CryptocurrencySortType.PRICE -> R.id.chip_price
            else -> 0
        }
    }

    private fun getSelectedSortDirectionItem(): Int {
        return when (args.params.sortDirection) {
            SortDirection.ASCENDING -> R.id.chip_ascending
            SortDirection.DESCENDING -> R.id.chip_descending
            else -> 0
        }
    }

    private fun getSelectedCryptoTypeItem(): Int {
        return when (args.params.cryptoType) {
            CryptocurrencyFilterType.ALL -> R.id.chip_crypto_all
            CryptocurrencyFilterType.COINS -> R.id.chip_coins
            CryptocurrencyFilterType.TOKENS -> R.id.chip_tokens
            else -> 0
        }
    }

    private fun getSelectedTagTypeItem(): Int {
        return when (args.params.tagType) {
            TagFilterType.ALL -> R.id.chip_tag_all
            TagFilterType.DEFIDEFI -> R.id.chip_defi
            TagFilterType.FILE_SHARING -> R.id.chip_file_sharing
            else -> 0
        }
    }

    private fun setupListeners() {
        binding.chGroupSortBy.setOnCheckedChangeListener { _, checkedId ->
            viewModel.setSortType(
                when (checkedId) {
                    R.id.chip_market_cap -> CryptocurrencySortType.MARKET_CAP
                    R.id.chip_name -> CryptocurrencySortType.NAME
                    R.id.chip_price -> CryptocurrencySortType.PRICE
                    else -> CryptocurrencySortType.PRICE
                }
            )
        }
        binding.chGroupSortDirection.setOnCheckedChangeListener { _, checkedId ->
            viewModel.setSortDirection(
                when (checkedId) {
                    R.id.chip_ascending -> SortDirection.ASCENDING
                    else -> SortDirection.DESCENDING
                }
            )
        }
        binding.chGroupCryptoType.setOnCheckedChangeListener { _, checkedId ->
            viewModel.setCryptoType(
                when (checkedId) {
                    R.id.chip_crypto_all -> CryptocurrencyFilterType.ALL
                    R.id.chip_coins -> CryptocurrencyFilterType.COINS
                    R.id.chip_tokens -> CryptocurrencyFilterType.TOKENS
                    else -> CryptocurrencyFilterType.ALL
                }
            )
        }

        binding.chGroupTagType.setOnCheckedChangeListener { _, checkedId ->
            viewModel.setTagType(
                when (checkedId) {
                    R.id.chip_tag_all -> TagFilterType.ALL
                    R.id.chip_defi -> TagFilterType.DEFIDEFI
                    R.id.chip_file_sharing -> TagFilterType.FILE_SHARING
                    else -> TagFilterType.ALL
                }
            )
        }
        binding.fabDone.setOnClickListener {
            val filterParams = SortAndFilterParams(
                viewModel.sortType.value,
                viewModel.sortDirection.value,
                viewModel.cryptoType.value,
                viewModel.tagType.value
            )
            setFragmentResult(Constants.KEY_FILTER_PARAMS, filterParams)
            dismiss()
        }
    }
}