package com.chablis.frame.base.rx

import com.chablis.frame.base.model.BaseResult
import com.chablis.modern.rx.ApiException
import io.reactivex.functions.Function

/**
 * Created by chablis on 2017/11/20.
 * 暂时不需要用
 */
class HttpResultFunc<T> : Function<BaseResult<T>, T> {
    @Throws(Exception::class)
    override fun apply(t: BaseResult<T>): T{
        //response中code码不为200 出现错误 if (!t.isOk())
        //抛出异常，把状态码及状态描述信息传入
        if (t.isOk()) {
            if (t.msg == null) t.msg = t.exception
            throw ApiException(t.code!!, t.msg!!)
        }
        return t.data!!
    }


}