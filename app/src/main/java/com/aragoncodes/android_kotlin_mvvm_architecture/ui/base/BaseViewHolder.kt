package com.aragoncodes.android_kotlin_mvvm_architecture.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private var mCurrentPosition: Int = 0

    protected abstract fun clear()

    open fun onBind(position: Int) {
        mCurrentPosition = position
        clear()
    }

    fun getCurrentPosition(): Int {
        return mCurrentPosition
    }

}