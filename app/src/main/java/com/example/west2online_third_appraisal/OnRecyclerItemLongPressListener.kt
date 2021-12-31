package com.example.west2online_third_appraisal

import android.view.View
/**
 * @Author Gallon
 * @Package com.example.west2online_third_appraisal
 * @Description 实现item长按事件接口
 * @Time 2022-12-31,周五 17:21
 */
interface OnRecyclerItemLongPressListener {
    fun onItemLongPress(position: Int, childView: View?)
}