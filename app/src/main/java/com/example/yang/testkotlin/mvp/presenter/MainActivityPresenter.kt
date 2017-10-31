package com.example.yang.testkotlin.mvp.presenter

import com.example.yang.testkotlin.mvp.contract.MainContract

/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
class MainActivityPresenter(override var mView: MainContract.View?) : MainContract.Presenter {

    override fun requestModel() {
    }

    override fun onDetach() {
        mView = null
    }
}