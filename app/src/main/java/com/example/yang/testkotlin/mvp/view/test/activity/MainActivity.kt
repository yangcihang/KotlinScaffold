package com.example.yang.testkotlin.mvp.view.test.activity

import com.example.yang.testkotlin.R
import com.example.yang.testkotlin.base.NoBarActivity
import com.example.yang.testkotlin.mvp.contract.MainContract
import com.example.yang.testkotlin.mvp.presenter.MainActivityPresenter
import com.example.yang.testkotlin.network.NetWork
import com.example.yang.testkotlin.network.RspCallback
import com.example.yang.testkotlin.util.ToastUtil
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : NoBarActivity(), MainContract.View {
    override val mPresenter: MainContract.Presenter
        get() = MainActivityPresenter()


    override fun onDataLoadSuccess() {
        ToastUtil.showToast("231")
    }

    override fun loadData() {

    }

    override fun initView() {
        println("123")
        txt_say_hello.setOnClickListener {
            mPresenter.requestModel()
        }
        btn_to_test.setOnClickListener {
            NetWork.getService().requestUnstartList("1", "1").enqueue(object : RspCallback<String>() {
                override fun onSuccess(data: String) {
                }

                override fun onFailed() {
                }

            })
        }

    }

    override fun initVariable() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }
}