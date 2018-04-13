package com.chablis.frame.base.utils

import com.chablis.frame.base.view.BaseActivity

class CHProgress (mActivity:BaseActivity) {
    companion object {
        @Volatile
        var instance: CHProgress? = null

        fun getInstance(mActivity:BaseActivity): CHProgress {
            if (instance == null) {
                synchronized(CHProgress::class) {
                    if (instance == null) {
                        instance = CHProgress(mActivity)
                    }
                }
            }
            return instance!!
        }

        fun show(){}
        fun dismiss(){}
    }
}