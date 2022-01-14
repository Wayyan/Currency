package com.wayyan.currency.base.core

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<viewHolder : BaseRecyclerViewHolder<Model>, Model>(context: Context) :
    RecyclerView.Adapter<viewHolder>() {

    val CODE_ITEM_CLICK = Code.StringCode("BaseRecyclerAdapter@ItemClicked")

    val mLayoutInflater: LayoutInflater by lazy { LayoutInflater.from(context) }
    var mData: MutableList<Model> = ArrayList()

    protected var delegate: BaseDelegate<Model>? = null

    fun addDelegate(delegate: BaseDelegate<Model>) {
        this.delegate = delegate
    }

    override fun getItemCount() = mData.size

    fun setNewData(newData: List<Model>) {
        mData = newData.toMutableList()
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Model? {
        if (position < mData.size && position > -1)
            return mData[position]
        return null
    }

    fun getItems(): List<Model> {
        if (mData.isNullOrEmpty())
            return emptyList()
        return mData
    }

    fun removeData(data: Model) {
        mData.remove(data)
        notifyDataSetChanged()
    }

    fun addNewData(data: Model) {
        mData.add(data)
        notifyDataSetChanged()
    }

    fun addNewData(
        data: Model,
        position: Int
    ) {
        mData.add(data)
        notifyDataSetChanged()
    }

    fun addDataAtPositionZero(data: Model) {
        mData.add(0, data)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData.clear()
        notifyDataSetChanged()
    }

    fun removeDataAt(position: Int) {
        mData.removeAt(position)
        notifyDataSetChanged()
    }
}