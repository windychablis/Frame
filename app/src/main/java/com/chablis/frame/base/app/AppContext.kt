package com.chablis.frame.base.app

/**
 * Created by chablis on 2016/11/7.
 */

import android.app.Application
import com.chablis.frame.base.rx.ApiClient

class AppContext : Application() {


    override fun onCreate() {
        super.onCreate()
        sInstance = this
        ApiClient.instance.init()
    }

    companion object {

        private var sInstance: Application? = null

        fun get(): Application? = sInstance
    }

}
