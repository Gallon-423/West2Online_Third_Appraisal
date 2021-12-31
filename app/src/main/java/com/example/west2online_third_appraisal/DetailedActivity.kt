package com.example.west2online_third_appraisal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.databinding.DataBindingUtil
import com.example.west2online_third_appraisal.databinding.DetailedActivityBinding

/**
 * @Author Gallon
 * @Package com.example.west2online_third_appraisal
 * @Description DetailActivity根据传入的信息构建活动，并返回相应应该删除的position
 * @Time 2022-12-31,周五 17:19
 */
class DetailedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil
            .setContentView<DetailedActivityBinding>(
                this,
                R.layout.detailed_activity
            )
        val position=intent.getIntExtra("currentPosition",0)
        val headImage=intent.getIntExtra("headImage",0)
        val videoImage=intent.getIntExtra("videoImage",0)
        val intro=intent.getIntExtra("intro",0)
        Log.d("DetailedActivity","获取主活动信息成功")
        binding.tv1.text="粉丝数"
        binding.tv2.text="点赞总数"
        binding.imageView3.setImageResource(headImage)
        binding.imageView4.setImageResource(videoImage)
        Log.d("DetailedActivity","布局文件资源已加载")
        binding.button.setOnClickListener {
            val intent = Intent()
            intent.putExtra("delete",position)
            binding.button.setBackgroundColor(Color.GRAY)
            Log.d("DetailedActivity","已放入待删除position")
            Log.d("DetailedActivity","已删除条目数据")
            setResult(Activity.RESULT_OK, intent)
            binding.button.text="未关注"
            binding.button.isClickable=false
            Log.d("DetailedActivity",position.toString() + "号被删除")
            Toast.makeText(application, "取关成功", Toast.LENGTH_SHORT).show();
            Log.d("DetailedActivity","已对当前$position 号取消关注")
        }
        binding.imageView5.setOnClickListener { finish() }
    }
}