package com.example.yang.testkotlin.mvp.view.test.activity

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.example.yang.testkotlin.R
import com.example.yang.testkotlin.base.ToolbarActivity
import com.example.yang.testkotlin.mvp.view.test.adapter.TestAdapter
import com.example.yang.testkotlin.util.ToastUtil
import kotlinx.android.synthetic.main.activity_main2.*


class MainActivity2 : ToolbarActivity() {
    lateinit var adapter: TestAdapter

    override fun initVariable() {
        adapter = TestAdapter(this)
        adapter.onClickedListener = {
            model, position ->
            ToastUtil.showToast(model + position)
        }
    }


    override fun initView() {
        //设置activity进场动画
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setActivityTitle("T")
        txt_main2.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        rec_test.layoutManager = LinearLayoutManager(this)
        rec_test.adapter = adapter
        expand_view.setOnClickListener {
            if (!expand_view.isPlaying) {
                if (!expand_view.isShowing) expand_view.playAnim() else expand_view.stopAnim()
            }
        }
        btn_to_scroller.setOnClickListener { startActivity(Intent(this, ScrollerTestActivity::class.java)) }
        btn_to_drag.setOnClickListener { startActivity(Intent(this, DragerTestActivity::class.java))}

    }

    override fun loadData() {
        val list: MutableList<String> = ArrayList()
        for (i in 0..10) {
            list.add(" ;$")
        }
        adapter.addAll(list)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main2
    }

    override fun onDestroy() {
        super.onDestroy()
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

}
