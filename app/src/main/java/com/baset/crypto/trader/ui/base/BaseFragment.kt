package com.baset.crypto.trader.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.baset.crypto.trader.BR
import com.baset.crypto.trader.R
import com.baset.crypto.trader.ui.main.MainActivity
import com.baset.crypto.trader.utils.factory.ViewModelFactory

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val layoutResourceId: Int) :
    Fragment() {

    protected abstract val viewModel: VM
    private var realBinding: B? = null
    protected val binding: B
        get() = realBinding ?: throw IllegalStateException("Trying to access the binding outside of the view lifecycle.")
    private var _firstTimeInitialization = false
    protected lateinit var activity: MainActivity
    protected abstract val viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return DataBindingUtil.inflate<B>(inflater, layoutResourceId, container, false).also {
            _firstTimeInitialization = false
            realBinding = it
        }.root
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!_firstTimeInitialization) {
            // It means that first time we apply lifeCycle and ViewModel
            binding.setVariable(BR.viewModel, viewModel)
            singleInit()
            _firstTimeInitialization = true
        }
        // This is necessary for dataBinding inside xml.
        binding.lifecycleOwner = viewLifecycleOwner
        startObserve()
    }

    protected open fun singleInit() {}

    /**
     * observe view model live data and state flow
     * for collect single flow use Flow.flowWithLifecycle(lifecycle, Lifecycle.State.state)
     * for collect collect flow with expensive object use lifecycle.repeatOnLifecycle(Lifecycle.State.state)
     * for collect multiple flow use viewLifecycleOwner.addRepeatingJob(Lifecycle.State.state)
     */
    protected open fun startObserve() {}

    override fun onDestroyView() {
        super.onDestroyView()
        if (realBinding != null)
            realBinding = null
    }

    open fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    open fun showError(message: String?) {
        showMessage(message ?: requireContext().getString(R.string.error_during_action))
    }
}