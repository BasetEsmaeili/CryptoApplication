package com.baset.crypto.trader.ui.cryptolist

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.baset.crypto.data.utils.network.NetworkManager
import com.baset.crypto.domain.entity.ErrorEntity
import com.baset.crypto.trader.R
import com.baset.crypto.trader.adapter.CryptoListAdapter
import com.baset.crypto.trader.databinding.FragmentCryptoListBinding
import com.baset.crypto.trader.di.app.findAppComponent
import com.baset.crypto.trader.di.cryptolist.DaggerCryptoListComponent
import com.baset.crypto.trader.entity.SortAndFilterParams
import com.baset.crypto.trader.ui.base.BaseFragment
import com.baset.crypto.trader.utils.Constants
import com.baset.crypto.trader.utils.factory.CryptoItemFactory
import com.baset.crypto.trader.utils.factory.ViewModelFactory
import com.baset.crypto.trader.utils.getFragmentResult
import com.baset.crypto.trader.utils.isVisible
import com.baset.crypto.trader.utils.pagination.InfiniteScrollProvider
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CryptoListFragment :
    BaseFragment<FragmentCryptoListBinding, CryptoListViewModel>(R.layout.fragment_crypto_list) {
    @Inject
    lateinit var cryptoItemFactory: CryptoItemFactory

    @Inject
    override lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var networkManager: NetworkManager
    override val viewModel: CryptoListViewModel by viewModels { viewModelFactory }
    private lateinit var adapter: CryptoListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerCryptoListComponent.builder().appComponent(findAppComponent()).build().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CryptoListAdapter(cryptoItemFactory)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCryptoList()
        checkFilterAvailable()
        setupListeners()
    }

    override fun onStart() {
        super.onStart()
        loadCryptocurrencies()
    }

    override fun onResume() {
        super.onResume()
        getSortAndFilterResult()
    }

    private fun loadCryptocurrencies() {
        if (networkManager.isNetworkAvailable())
            viewModel.getCryptocurrencies()
    }

    private fun getSortAndFilterResult() {
        getFragmentResult<SortAndFilterParams>(
            R.id.fragment_crypto_list,
            Constants.KEY_FILTER_PARAMS
        ) {
            if (isFilterParamsChanged(it)) {
                setFilterParams(it)
                adapter.submitList(emptyList())
                viewModel.resetContent()
                viewModel.getCryptocurrencies()
            }
        }
    }

    private fun setFilterParams(params: SortAndFilterParams) {
        viewModel.setSortType(params.sortType)
        viewModel.setSortDirection(params.sortDirection)
        viewModel.setCryptoType(params.cryptoType)
        viewModel.setTagType(params.tagType)
    }

    private fun isFilterParamsChanged(params: SortAndFilterParams): Boolean {
        return params.sortType != viewModel.sortType.value
                || params.sortDirection != viewModel.sortDirection.value
                || params.cryptoType != viewModel.cryptoType.value
                || params.tagType != viewModel.tagType.value
    }

    private fun setupListeners() {
        binding.fabFilter.setOnClickListener {
            findNavController().navigate(
                CryptoListFragmentDirections.actionFragmentCryptoListToDialogSortAndFilter(
                    SortAndFilterParams(
                        viewModel.sortType.value,
                        viewModel.sortDirection.value,
                        viewModel.cryptoType.value,
                        viewModel.tagType.value
                    )
                )
            )
        }
    }

    private fun checkFilterAvailable() {
        binding.fabFilter.isVisible(networkManager.isNetworkAvailable())
    }

    override fun startObserve() {
        super.startObserve()
        viewModel.cryptocurrencies.flowWithLifecycle(lifecycle)
            .onEach {
                val newList = adapter.currentList + it
                adapter.submitList(newList)
                setEmptyViewState(it.isNullOrEmpty())
            }.launchIn(lifecycleScope)

        viewModel.errorEvent.flowWithLifecycle(lifecycle)
            .onEach {
                parseError(it)
            }.launchIn(lifecycleScope)

        viewModel.isShowLoading.observe(viewLifecycleOwner) {
            setLoadingState(it)
        }
    }

    private fun parseError(errorEntity: ErrorEntity?) {
        when (errorEntity) {
            is ErrorEntity.ApiError.NoNetwork -> {
                showError(getString(R.string.error_no_connection))
            }
            is ErrorEntity.ApiError.Timeout -> {
                showError(getString(R.string.error_request_timeout))
            }
            is ErrorEntity.ApiError.UnAuthorized -> {
                showError(getString(R.string.error_unauthorized))
            }

            is ErrorEntity.ApiError.BadRequest -> {
                showError(getString(R.string.error_bad_request))
            }
            is ErrorEntity.ApiError.Forbidden -> {
                showError(getString(R.string.error_forbidden))
            }
            is ErrorEntity.ApiError.ToManyRequests -> {
                showError(getString(R.string.error_to_many_request))
            }
            is ErrorEntity.ApiError.InternalServerError -> {
                showError(getString(R.string.error_server_error))
            }
            is ErrorEntity.ApiError.Unknown -> {
                showError("StatusCode:${errorEntity.statusCode} Message:${errorEntity.message}")
            }
        }
    }


    private fun setupCryptoList() {
        binding.rvCryptocurrencies.adapter = adapter
        binding.rvCryptocurrencies.setHasFixedSize(false)
        setupCryptoListPagination()
        setupCryptoListScrollListener()
    }

    private fun setupCryptoListPagination() {
        binding.rvCryptocurrencies.addOnScrollListener(object : InfiniteScrollProvider() {

            override fun isLastPage(): Boolean {
                return viewModel.isLastPage.value
            }

            override fun onLoadMore() {
                if (networkManager.isNetworkAvailable())
                    viewModel.getCryptocurrencies()
            }
        })
    }

    private fun setupCryptoListScrollListener() {
        binding.rvCryptocurrencies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                animateAppNameView((binding.rvCryptocurrencies.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() == 0)
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }

    private fun setEmptyViewState(isVisible: Boolean) {
        if (isVisible)
            binding.lottieEmptyView.playAnimation()
        else {
            binding.lottieEmptyView.progress = 0f
            binding.lottieEmptyView.pauseAnimation()
        }
        binding.lottieEmptyView.isVisible(isVisible)
        binding.tvEmptyState.isVisible(isVisible)
    }

    private fun setLoadingState(isVisible: Boolean) {
        if (isVisible)
            binding.lottieLoading.playAnimation()
        else {
            binding.lottieLoading.progress = 0f
            binding.lottieLoading.pauseAnimation()
        }
        binding.lottieLoading.isVisible(isVisible)
    }

    private fun animateAppNameView(isVisible: Boolean) {
        if (isVisible)
            binding.tvAppName.animate().alpha(1f).setDuration(150).start()
        else
            binding.tvAppName.animate().alpha(0f).setDuration(150).start()
    }
}