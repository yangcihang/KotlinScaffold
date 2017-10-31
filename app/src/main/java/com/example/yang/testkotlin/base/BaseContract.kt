package com.example.yang.testkotlin.base


/**
 * @author YangCihang
 * @since  17/9/24.
 * email yangcihang@hrsoft.net
 */
interface BaseContract {
    /**
     * 进行双向绑定
     */
    interface View<T> {
        var mPresenter: T?

    }

    interface Presenter<T> {
        var mView: T?
        //解除绑定
        fun onDetach(): Unit
    }
}
