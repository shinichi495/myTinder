package com.namph.mytinder.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.namph.mytinder.BR

abstract class BaseBindingAdapter<B : ViewDataBinding, T>(
    context: Context,
    @LayoutRes val resId: Int
) : RecyclerView.Adapter<BaseBindingAdapter.ViewHolder<B>>() {
    lateinit var binding: B
    var inflater: LayoutInflater
    lateinit var data : List<T>
    init {
        inflater = LayoutInflater.from(context)
    }

    fun setItems(data: List<T>) {
        this.data = data
    }

    fun getItems(): List<T> {
        return this.data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> {
        binding = DataBindingUtil.inflate(inflater, resId, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
        val item: T = data.get(position)
        holder.viewDataBinding.setVariable(BR.item, item)
        holder.viewDataBinding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ViewHolder<T : ViewDataBinding>(val viewDataBinding: T) :
        RecyclerView.ViewHolder(viewDataBinding.root) {}

}