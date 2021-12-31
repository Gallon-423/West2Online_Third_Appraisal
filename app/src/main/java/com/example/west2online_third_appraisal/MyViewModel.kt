package com.example.west2online_third_appraisal

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
/**
 * @Author Gallon
 * @Package com.example.west2online_third_appraisal
 * @Description 存储数据的viewModel类
 * @Time 2022-12-31,周五 17:20
 */
class MyViewModel: ViewModel() {
    private var _items: MutableList<UpItem>  = mutableListOf()
    val items: MutableList<UpItem>
        get() = _items
    private val _itemCount = 7
    private var _MsgItems: MutableList<MsgItem>  = mutableListOf()
    val MsgItems: MutableList<MsgItem>
        get() = _MsgItems
    //初始化
    init {
        for(it in 0 until _itemCount){
            _items.add(UpItem(upItemNameList[it], headImageList[it]))
            _MsgItems.add(MsgItem(upItemNameList[it], headImageList[it], videoImageList[it]
                ,introductions[it], videoNames[it]))
        }
        Log.d("ViewModel","ViewModel Created ！创建Model ！"+this.hashCode())
    }

    override fun onCleared() {
        Log.d("ViewModel","ViewModel 被摧毁 ！")
    }
    fun removeItem(position:Int){
        _items.removeAt(position)
        _MsgItems.removeAt(position)
    }
}