package com.chablis.frame.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.chablis.frame.model.User
import com.chablis.frame.model.User1

/**
 * Created by chablis on 2017/11/12.
 */
class FirstViewModel: ViewModel() {
    var user: MutableLiveData<User1>?=null
    fun getUser(): LiveData<User1> {
        if (user == null) {
            user = MutableLiveData()
            loadData()
        }
        return user as MutableLiveData<User1>
    }

    /**
     *
     * 模拟网络请求获取数据,获取之后直接修改数据,界面也会对应的修改
     *
     */
    fun loadData(){
        var a=Math.random()
        user?.postValue(User1("小虾米${a}",2))
    }



}