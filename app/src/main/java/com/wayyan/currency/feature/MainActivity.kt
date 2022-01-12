package com.wayyan.currency.feature

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import com.wayyan.currency.base.core.BaseActivity
import com.wayyan.currency.base.helper.AsyncViewState
import com.wayyan.currency.databinding.ActivityMainBinding
import com.wayyan.currency.extension.showShortToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>() {
  override val binding: ActivityMainBinding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
  }
  val viewModel: MainViewModel by viewModel()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun initUi() {
    viewModel.getCurrencyData()
  }

  override fun initListener() {
  }

  override fun observeLiveData(owner: LifecycleOwner) {
    viewModel.currencyNamesLiveData.observe(owner, {
      when (it) {
        is AsyncViewState.Loading -> {
          Timber.d("Loading")
        }

        is AsyncViewState.Error -> {
          Timber.d(it.exception)
        }

        is AsyncViewState.Success -> {
          it.value.forEach {
            Timber.d(it)
          }
          this.showShortToast("Fetched >> ${it.value.size}")
        }
      }
    })
  }
}