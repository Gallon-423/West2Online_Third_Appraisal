package com.example.west2online_third_appraisal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TableLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.MediatorLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private var upItemList=ArrayList<UpItem>();
    var recyclerView:RecyclerView?=null;
    var viewPager2:ViewPager2?=null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bilibili_simulation_layout)
        initUpItemList();
        //为recyclerView添加布局管理器和适配器
        recyclerView=findViewById(R.id.recycler)
        val layoutManager=LinearLayoutManager(this)
        layoutManager.orientation=LinearLayoutManager.HORIZONTAL
        recyclerView?.layoutManager=layoutManager;
        val adapter = UpItemAdapter(upItemList)
        recyclerView?.adapter=adapter
        //为viewPager2添加适配器
        viewPager2=findViewById(R.id.viewPager2)
        viewPager2?.adapter=object :FragmentStateAdapter(this){
            override fun getItemCount()=upItemList.size
            override fun createFragment(position: Int)=RawFragment()
        }



    }
    private fun initUpItemList(){
        upItemList.add(UpItem("黄四郎、",R.drawable.boss))
        upItemList.add(UpItem("骗子院长",R.drawable.liar))
        upItemList.add(UpItem("师爷好我是张麻子",R.drawable.leader))
        upItemList.add(UpItem("老举人",R.drawable.boxer))
        upItemList.add(UpItem("师爷说刑法",R.drawable.master))
        upItemList.add(UpItem("花姐Kaze",R.drawable.flower))
    }
}