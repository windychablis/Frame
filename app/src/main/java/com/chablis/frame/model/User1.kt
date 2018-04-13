package com.chablis.frame.model

import android.databinding.BaseObservable
import android.databinding.Bindable

import com.chablis.frame.BR

/**
 * databinding双向绑定需要的配置
 */
class User1(private var name: String?, private var age: Int) : BaseObservable() {

    @Bindable
    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
        notifyPropertyChanged(BR.name)
    }

    @Bindable
    fun getAge(): Int {
        return age
    }

    fun setAge(age: Int) {
        this.age = age
        notifyPropertyChanged(BR.age)
    }
}
