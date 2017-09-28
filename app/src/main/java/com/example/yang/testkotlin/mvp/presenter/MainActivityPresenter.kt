package com.example.yang.testkotlin.mvp.presenter

import com.example.yang.testkotlin.mvp.contract.MainContract
import com.example.yang.testkotlin.mvp.view.test.activity.MainActivity
import com.example.yang.testkotlin.util.ToastUtil

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
class MainActivityPresenter : MainContract.Presenter {
    override val mView: MainContract.View
        get() = MainActivity()

    override fun requestModel() {
        ToastUtil.showToast("123")
        mView.onDataLoadSuccess()
    }
}