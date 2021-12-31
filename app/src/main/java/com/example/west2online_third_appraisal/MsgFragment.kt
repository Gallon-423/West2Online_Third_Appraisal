package com.example.west2online_third_appraisal
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.west2online_third_appraisal.databinding.FragmentToShowMsgBinding


class MsgFragment(private val position : Int): Fragment() {
    //获取父活动中的viewModel使用activityViewModels的委派属性,否则会再创建一个实例
    //返回一个属性委托来访问父活动的 ViewModel
    private val activityViewModel: MyViewModel by activityViewModels()
    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding=FragmentToShowMsgBinding.inflate(inflater)
        fragmentBinding.nameTv.text=activityViewModel.MsgItems[position].name+" 的动态信息"
        fragmentBinding.introsTv.text=activityViewModel.MsgItems[position].intro
        fragmentBinding.vedioImage.setImageResource(activityViewModel.MsgItems[position].videoImage)
        fragmentBinding.imageView2.setImageResource(activityViewModel.MsgItems[position].headImageId)
        fragmentBinding.videoNameTv.text=activityViewModel.MsgItems[position].videoName
        return fragmentBinding.root

    }

}