package com.chablis.modern.rx

/**
 * Created by chablis on 2016/8/4.
 * 暂时不需要用
 */
class ApiException(val code: Int,msg:String) : Exception(msg) {
    //用于展示的异常信息
    var displayMessage: String? = null
}
