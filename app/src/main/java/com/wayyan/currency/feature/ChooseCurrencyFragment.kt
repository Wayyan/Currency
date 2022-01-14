package com.wayyan.currency.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wayyan.currency.R
import com.wayyan.currency.adapter.CurrencyNameAdapter
import com.wayyan.currency.base.core.BaseDelegate
import com.wayyan.currency.base.core.BaseRecyclerAdapter
import com.wayyan.currency.base.core.Code
import com.wayyan.currency.base.helper.AsyncViewState
import com.wayyan.currency.databinding.FragmentChooseCurrencyBinding
import com.wayyan.currency.extension.shouldDismiss
import com.wayyan.currency.extension.shouldShow
import com.wayyan.currency.extension.showActionPromptDialog
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ChooseCurrencyFragment : BottomSheetDialogFragment(), KoinComponent {
  val viewModel: MainViewModel by sharedViewModel()
  val currencyNameAdapter: CurrencyNameAdapter by inject()

  private val binding: FragmentChooseCurrencyBinding by lazy {
    FragmentChooseCurrencyBinding.inflate(requireActivity().layoutInflater)
  }

  companion object {
    const val TAG = "ChooseCurrencyFragment"
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
    initListener()
    observeLiveData(viewLifecycleOwner)
  }

  private fun initUi() {
    binding.recyclerChooseCurrency.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(requireContext())
      adapter = currencyNameAdapter
    }
  }

  private fun initListener() {
    currencyNameAdapter.addDelegate(object : BaseDelegate<String> {
      override fun onAction(code: Code, data: String?) {
        data?.let {
          viewModel.selectedCurrency = it
          dismiss()
        }
      }
    })
  }

  private fun observeLiveData(owner: LifecycleOwner) {
    viewModel.currencyNamesLiveData.observe(owner) {
      when (it) {
        is AsyncViewState.Loading -> {
        }

        is AsyncViewState.Error -> {
          requireContext().showActionPromptDialog(
            msg = it.errorMessage,
            btnText = resources.getString(R.string.lbl_retry)
          ) {
            viewModel.getCurrencyNames()
          }
        }

        is AsyncViewState.Success -> {
          currencyNameAdapter.setNewData(it.value, viewModel.selectedCurrency )
        }
      }
    }
  }
}