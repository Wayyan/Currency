package com.wayyan.currency.adapter

import android.content.Context
import android.view.ViewGroup
import com.wayyan.currency.adapter.CurrencyDataAdapter.CurrencyDataViewHolder
import com.wayyan.currency.base.core.BaseRecyclerAdapter
import com.wayyan.currency.base.core.BaseRecyclerViewHolder
import com.wayyan.currency.databinding.ItemCurrencyDataBinding
import com.wayyan.currency.domain.currency.model.CurrencyModel
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class CurrencyDataAdapter constructor(private val context: Context) :
  BaseRecyclerAdapter<CurrencyDataViewHolder, CurrencyModel>(context) {
  var amount: Double = 1.0
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  private val decimalFormat = DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH))

  init {
    decimalFormat.maximumFractionDigits = 25
  }

  inner class CurrencyDataViewHolder(private val binding: ItemCurrencyDataBinding) :
    BaseRecyclerViewHolder<CurrencyModel>(binding.root) {
    override fun bind(model: CurrencyModel) {
      binding.tvName.text = model.currencyName

      val value = model.currencyValue * amount
      binding.tvValue.text = decimalFormat.format(value).toString()
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyDataViewHolder {
    val binding = ItemCurrencyDataBinding.inflate(mLayoutInflater, parent, false)
    return CurrencyDataViewHolder(binding)
  }

  override fun onBindViewHolder(holder: CurrencyDataViewHolder, position: Int) {
    holder.bind(mData[position])
  }
}