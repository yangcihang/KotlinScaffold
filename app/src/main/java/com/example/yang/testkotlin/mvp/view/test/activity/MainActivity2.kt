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
        setActivityTitle("2345")
        txt_main2.setOnClickListener { startActivity(Intent(this@MainActivity2, MainActivity::class.java)) }
        rec_test.layoutManager = LinearLayoutManager(this)
        rec_test.adapter = adapter
    }

    override fun loadData() {
        val list: MutableList<String> = ArrayList()
        for (i in 0..10) {
            list.add("123")
        }
        adapter.addAll(list)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main2
    }

}
