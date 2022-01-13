package com.wayyan.currency.feature

import android.app.Dialog
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.wayyan.currency.R
import com.wayyan.currency.adapter.CurrencyDataAdapter
import com.wayyan.currency.base.core.BaseActivity
import com.wayyan.currency.base.helper.AsyncViewState
import com.wayyan.currency.databinding.ActivityMainBinding
import com.wayyan.currency.extension.getDialog
import com.wayyan.currency.extension.shouldDismiss
import com.wayyan.currency.extension.shouldShow
import com.wayyan.currency.extension.showActionPromptDialog
import com.wayyan.currency.extension.showPromptDialog
import com.wayyan.currency.extension.showShortToast
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>() {
  override val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
  }
  val viewModel: MainViewModel by viewModel()
  val currencyDataAdapter: CurrencyDataAdapter by inject()

  private lateinit var loadingDialog: Dialog

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    loadingDialog = this.getDialog(R.layout.dialog_loading)
  }

  override fun initUi() {
    binding.tvCurrencyName.text = viewModel.selectedCurrency

    viewModel.getCurrencyNames()

    binding.recyclerCurrencyData.apply {
      setHasFixedSize(true)
      layoutManager = GridLayoutManager(context, 2)
      adapter = currencyDataAdapter
    }
  }

  override fun initListener() {
    binding.layoutSelectCurrency.setOnClickListener {
      val existingFragment =
        supportFragmentManager.findFragmentByTag(ChooseCurrencyFragment.TAG)
      if (existingFragment == null) {
        ChooseCurrencyFragment().show(
          supportFragmentManager,
          ChooseCurrencyFragment.TAG
        )
      }

    }

    binding.edAmount.doOnTextChanged { text, start, before, count ->
      val value =
        if (text.isNullOrEmpty()) {
          1.0
        } else {
          text.toString().toDouble()
        }

      currencyDataAdapter.amount = value
    }
  }

  override fun observeLiveData(owner: LifecycleOwner) {
    viewModel.currencyNamesLiveData.observe(owner) {
      when (it) {
        is AsyncViewState.Loading -> {
          loadingDialog.shouldShow()
        }

        is AsyncViewState.Error -> {
          loadingDialog.shouldDismiss()
          this.showActionPromptDialog(
            msg = it.errorMessage,
            btnText = resources.getString(R.string.lbl_retry)
          ) {
            viewModel.getCurrencyNames()
          }
        }

        is AsyncViewState.Success -> {
          loadingDialog.shouldDismiss()
          viewModel.getCurrencyData()
        }
      }
    }

    viewModel.currencyDataLiveData.observe(owner) {
      when (it) {
        is AsyncViewState.Loading -> {
          loadingDialog.shouldShow()
        }

        is AsyncViewState.Error -> {
          loadingDialog.shouldDismiss()
          this.showPromptDialog(
            msg = it.errorMessage,
            btnText = resources.getString(R.string.lbl_retry)
          )
        }

        is AsyncViewState.Success -> {
          loadingDialog.shouldDismiss()
          currencyDataAdapter.setNewData(it.value)
          binding.recyclerCurrencyData.scrollToPosition(0)
        }
      }
    }

    viewModel.selectedCurrencyLiveData.observe(owner) {
      if (it is AsyncViewState.Success) {
        binding.tvCurrencyName.text = it.value
      }
    }
  }
}