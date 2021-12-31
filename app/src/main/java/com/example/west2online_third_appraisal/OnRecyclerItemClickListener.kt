package com.example.west2online_third_appraisal

import android.view.View
/**
 * @Author Gallon
 * @Package com.example.west2online_third_appraisal
 * @Description 实现item单击事件接口
 * @Time 2022-12-31,周五 17:20
 */



interface OnRecyclerItemClickListener {

        fun onItemClick(position: Int, childView: View?)
}
