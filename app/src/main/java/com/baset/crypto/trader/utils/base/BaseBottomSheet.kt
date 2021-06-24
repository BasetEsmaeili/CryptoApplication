package com.baset.crypto.trader.utils.base

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.baset.crypto.trader.BR
import com.baset.crypto.trader.R
import com.baset.crypto.trader.ui.base.BaseViewModel
import com.baset.crypto.trader.ui.main.MainActivity
import com.baset.crypto.trader.utils.factory.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<B : ViewDataBinding, VM : BaseViewModel>(@LayoutRes private val layoutResourceId: Int) :
    BottomSheetDialogFragment() {


    protected abstract val viewModel: VM
    protected abstract val viewModelFactory: ViewModelFactory
    protected lateinit var activity: MainActivity
    private var realBinding: B? = null
    protected val binding: B
        get() = realBinding
            ?: throw IllegalStateException("Trying to access the binding outside of the view lifecycle.")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // We should stylize bottom sheet dialog fragment before inflating view happen.
        setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    /**
     * remove default bottom sheet background
     */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener {
                val bottomSheet =
                    findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
                bottomSheet.setBackgroundResource(android.R.color.transparent)
                // We should expand BottomSheetDialog to be EXPANDED to shows entire dialog when keyboard is open:
                (this as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        DataBindingUtil.inflate<B>(inflater, layoutResourceId, container, false).also {
            realBinding = it
        }.root

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(BR.viewModel, viewModel)
        startObserve()
    }


    override fun onStart() {
        super.onStart()
        (requireView().parent as View).setBackgroundColor(Color.TRANSPARENT)
    }

    /**
     * observe view model live data and state flow
     */
    protected open fun startObserve() {}

    override fun onDestroyView() {
        super.onDestroyView()
        realBinding = null
    }

    open fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    open fun showError(message: String?) {
        Toast.makeText(
            requireContext(),
            message ?: requireContext().getString(R.string.error_during_action),
            Toast.LENGTH_LONG
        ).show()
    }


    private fun shouldShowBottomSheet(manager: FragmentManager, tag: String?): Boolean =
        manager.findFragmentByTag(tag) == null

    override fun show(manager: FragmentManager, tag: String?) {
        if (shouldShowBottomSheet(manager, tag)) super.show(manager, tag)
    }

    override fun showNow(manager: FragmentManager, tag: String?) {
        if (shouldShowBottomSheet(manager, tag)) super.showNow(manager, tag)
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        findNavController().popBackStack()
    }
}