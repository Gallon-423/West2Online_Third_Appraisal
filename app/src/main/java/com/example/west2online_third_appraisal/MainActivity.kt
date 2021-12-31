package com.example.west2online_third_appraisal

import android.annotation.SuppressLint
import android.content.Intent
import com.example.west2online_third_appraisal.databinding.BilibiliSimulationBinding
//此处：dataBinding会将布局文件（指定的布局文件）自动生成类,并且自动将命名方式由下划线命名法改为大驼峰命名法
//视频的ActivityMainBinding之所以找不到是因为我的主布局文件不是默认的activity_main.xml(just a tip)
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View


import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    /**
     * @Author Gallon
     * @Package com.example.west2online_third_appraisal
     * @Description MainActivity
     * @Time 2022-12-26,周日 17:15
     */
    private val viewModel: MyViewModel by viewModels()
    private var gestureDetector: GestureDetector? = null;
    //用于为recyclerView每一个Item添加点击事件
    lateinit var binding:BilibiliSimulationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil
            .setContentView<BilibiliSimulationBinding>(
                this,
                R.layout.bilibili_simulation
            )
        //Log.d("MainActivity","活动创建！")
        setAdapterForViewPager()
        binding.viewPager2.setPageTransformer(ZoomOutPageTransformer())
        initRecycler()
        //根据回调数据进行处理
        val requestDataLauncher = callBackHandler()

        //长按事件
        val onRecyclerItemLongPressListener = object : OnRecyclerItemLongPressListener {
            override fun onItemLongPress(position: Int, childView: View?) {
                val intent = initIntent(position)
                //Toast.makeText(application, "长按事件:$position", Toast.LENGTH_SHORT).show();
                //startActivityForResult(intent,1)
                //startActivityForResult()被弃用了 改用Result API
                requestDataLauncher.launch(intent)
            }
        }
        //单击事件
        val onRecyclerItemClickListener: OnRecyclerItemClickListener = singleClickEvent()
        //以下是添加点击、长按事件的关键代码
        binding.recycler.addOnItemTouchListener(object : RecyclerView.SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (gestureDetector != null) {
                    if (gestureDetector!!.onTouchEvent(e)) {
                        return true;
                    }
                }
                return false;
            }
        });
        gestureDetector = object : GestureDetector(this, object :
            GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                val childView = e?.let { binding.recycler.findChildViewUnder(e.x, it.y) };
                if (childView != null) {
                    val position = binding.recycler.getChildLayoutPosition(childView);
                    onRecyclerItemClickListener.onItemClick(position, childView);

                }
                return super.onSingleTapUp(e)
            }

            override fun onLongPress(e: MotionEvent?) {
                val childView = e?.let { binding.recycler.findChildViewUnder(e.x, it.y) };
                if (childView != null) {
                    val position = binding.recycler.getChildLayoutPosition(childView);
                    onRecyclerItemLongPressListener.onItemLongPress(position, childView);
                }
                return super.onLongPress(e)
            }
        }){}


    }
    fun initRecycler(){
        //为recyclerView添加布局管理器和适配器
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recycler.layoutManager = layoutManager
        val adapter = UpItemAdapter(viewModel.items)
        binding.recycler.adapter = adapter
    }
    fun setAdapterForViewPager(){
        //为viewPager配置适配器
        binding.viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun containsItem(itemId: Long) = false
            override fun getItemId(position: Int) = viewModel.items[position].hashCode().toLong()
            override fun getItemCount() = viewModel.items.size
            override fun createFragment(position: Int) = MsgFragment(position)
        }
    }
    fun singleClickEvent():OnRecyclerItemClickListener{
        return object :
            OnRecyclerItemClickListener {
            override fun onItemClick(position: Int, childView: View?) {
                binding.viewPager2.currentItem = position
            }
        }
    }
    fun initIntent(position:Int):Intent{
        val intent = Intent(application, DetailedActivity::class.java)
        intent.putExtra("currentPosition", position)
        intent.putExtra("headImage",viewModel.MsgItems[position].headImageId)
        intent.putExtra("videoImage",viewModel.MsgItems[position].videoImage)
        intent.putExtra("intro",viewModel.MsgItems[position].intro)
        return intent
    }
    @SuppressLint("NotifyDataSetChanged")
    fun callBackHandler()=registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val deletePosition = result.data?.getIntExtra("delete", -1)
            if (deletePosition != null) {
                viewModel.removeItem(deletePosition)
            }
            for (it in viewModel.items) {
                Log.d("MainActivity", "当前up有：" + it.name)
            }
            binding.recycler.adapter?.notifyDataSetChanged()
            binding.viewPager2.adapter?.notifyDataSetChanged()
        }
    }

}
