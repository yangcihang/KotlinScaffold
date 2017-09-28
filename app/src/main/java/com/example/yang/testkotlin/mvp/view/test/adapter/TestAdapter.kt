package com.example.yang.testkotlin.mvp.view.test.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.yang.testkotlin.base.BaseRecyclerAdapter

/**
 * @author YangCihang
 * @since  17/9/26.
 * email yangcihang@hrsoft.net
 */
class TestAdapter(mContext: Context) : BaseRecyclerAdapter<String>(mContext) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder<String> {
        val itemView: TextView = TextView(mContext)
        return ItemHolder(itemView)
    }

    inner class ItemHolder(itemView: View?) : ViewHolder<String>(itemView) {
        override fun onBind(position: Int) {
            if (itemView is TextView) itemView.text = mData
            print("123")
        }
    }
}