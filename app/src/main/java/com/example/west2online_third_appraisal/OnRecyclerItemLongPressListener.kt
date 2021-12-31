package com.example.west2online_third_appraisal

import android.view.View

interface OnRecyclerItemLongPressListener {
    fun onItemLongPress(position: Int, childView: View?)
}