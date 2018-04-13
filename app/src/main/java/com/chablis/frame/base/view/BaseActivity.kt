package com.chablis.frame.base.view

import android.os.Bundle
import android.support.v4.app.FragmentManager
import com.chablis.frame.base.app.AppContext
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity


open class BaseActivity : RxAppCompatActivity() {
    var appContext: AppContext? = null
    var mActivity: BaseActivity? = null
    var mFragmentManager: FragmentManager? = null
    var TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        appContext = AppContext.get() as AppContext?
        mFragmentManager = supportFragmentManager
    }


}
