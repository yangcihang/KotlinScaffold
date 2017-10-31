package com.example.yang.testkotlin.mvp.view.test.activity

import android.graphics.drawable.AnimationDrawable
import com.example.yang.testkotlin.R
import com.example.yang.testkotlin.base.NoBarActivity
import com.example.yang.testkotlin.mvp.contract.MainContract
import com.example.yang.testkotlin.mvp.presenter.MainActivityPresenter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : NoBarActivity(), MainContract.View {
    override var mPresenter: MainContract.Presenter? = MainActivityPresenter(this)

    override fun onDataLoadSuccess() {
        btn_to_test.text = "123123123123"
    }

    override fun loadData() {
    }

    override fun initView() {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        txt_say_hello.apply {
            setOnClickListener { mPresenter!!.requestModel() }
            setOnTouchListener { view, motionEvent -> let { return@let false } }
        }
        img_test.apply {
            setBackgroundResource(R.drawable.test_frame)
            setOnClickListener {
                if ((background as AnimationDrawable).isRunning)
                    (background as AnimationDrawable).stop()
                else (background as AnimationDrawable).start()
            }
        }
        btn_to_test.setOnClickListener { mPresenter!!.requestModel() }
    }

    override fun initVariable() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onDestroy() {
        mPresenter?.onDetach()
        mPresenter = null
        super.onDestroy()
    }
}