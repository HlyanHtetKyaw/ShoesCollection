package com.test.visibleonecodingtest.adapters

import androidx.recyclerview.widget.RecyclerView
import com.test.visibleonecodingtest.viewholders.BaseViewHolder

abstract class BaseAdapter<T, W> : RecyclerView.Adapter<BaseViewHolder<W>>() {

    protected var mData: MutableList<W>? = null

    val items: List<W>
        get() {
            val data = mData
            return data ?: ArrayList()
        }

    init {
        mData = ArrayList()
    }

    override fun onBindViewHolder(holder: BaseViewHolder<W>, position: Int) {
        holder.setData(mData!![position])
    }

    override fun getItemCount(): Int {
        return mData!!.size
    }

    public fun dataRefresh() {
        notifyDataSetChanged()
    }

    fun setNewData(newData: MutableList<W>) {
        mData = newData
        notifyDataSetChanged()
    }

    fun appendNewData(newData: List<W>) {
        mData!!.addAll(newData)
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): W? {
        return getDataList().getOrNull(position)
    }

    fun removeData(data: W) {
        mData!!.clear()
        mData!!.remove(data)
        notifyDataSetChanged()
    }

    fun addNewData(data: W) {
        mData!!.add(data)
        notifyDataSetChanged()
    }

    fun addListData(newData: MutableList<W>) {
        mData!!.addAll(newData)
        notifyDataSetChanged()
    }

    fun clearData() {
        mData = ArrayList()
        notifyDataSetChanged()
    }

    fun getDataList(): MutableList<W> {
        return mData ?: mutableListOf()
    }

    fun removeList(position: Int) {
        mData!!.removeAt(position)
        notifyDataSetChanged()
    }

}