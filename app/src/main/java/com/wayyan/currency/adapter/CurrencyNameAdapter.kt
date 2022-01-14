package com.wayyan.currency.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import com.wayyan.currency.R
import com.wayyan.currency.adapter.CurrencyNameAdapter.CurrencyNameViewHolder
import com.wayyan.currency.base.core.BaseRecyclerAdapter
import com.wayyan.currency.base.core.BaseRecyclerViewHolder
import com.wayyan.currency.databinding.ItemCurrencyNameBinding

class CurrencyNameAdapter constructor(private val context: Context) :
  BaseRecyclerAdapter<CurrencyNameViewHolder, String>(context) {
  private var selected: String = ""

  inner class CurrencyNameViewHolder(private val binding: ItemCurrencyNameBinding) :
    BaseRecyclerViewHolder<String>(binding.root) {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun bind(model: String) {
      if (model == selected) {
        binding.root.background = context.getDrawable(R.drawable.selected_corner_8dp)
      } else {
        binding.root.background = context.getDrawable(R.drawable.dark_corner_8dp)
      }
      binding.root.setOnClickListener {
        delegate?.onAction(CODE_ITEM_CLICK, model)
      }
      binding.tvName.text = model
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyNameViewHolder {
    val binding = ItemCurrencyNameBinding.inflate(mLayoutInflater, parent, false)
    return CurrencyNameViewHolder(binding)
  }

  override fun onBindViewHolder(holder: CurrencyNameViewHolder, position: Int) {
    holder.bind(mData[position])
  }

  fun setNewData(data: List<String>, selected: String) {
    this.selected = selected
    setNewData(data)
  }
}