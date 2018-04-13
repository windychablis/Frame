package com.chablis.frame.base.model

/**
 * Created by chablis on 2017/11/18.
 */

class BaseResult<T>{

    /**
     * msg : 操作成功
     * code : 0
     * data : []
     */

    var msg: String? = null
    var code: Int? = null
    var exception:String?=null
    var data: T? = null
    fun isOk(): Boolean {
        return code == 0
    }

    override fun toString(): String {
        return "BaseResult(msg=$msg, code=$code, exception='$exception', data=$data)"
    }


}
