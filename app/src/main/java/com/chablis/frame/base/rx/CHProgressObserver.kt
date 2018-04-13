package com.chablis.frame.base.rx

import android.accounts.NetworkErrorException
import android.net.ParseException
import com.chablis.frame.base.model.BaseResult
import com.chablis.frame.base.utils.CHProgress
import com.chablis.frame.base.view.BaseActivity
import com.google.gson.JsonParseException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


/**
 * Created by chablis on 2017/11/21.
 */
abstract class CHProgressObserver<T>(mActivity: BaseActivity) : Observer<BaseResult<T>> {

    abstract fun onSuccess(s: T)

    abstract fun onFailure(s: String)

    override fun onSubscribe(d: Disposable) {
        CHProgress.show()
    }

    override fun onNext(t: BaseResult<T>) {
        if (t.code == 0) {
            onSuccess(t.data!!)
        } else {
            if (t.msg==null){
                t.exception?.let { onFailure(it) }
            }else{
                onFailure(t.msg!!)
            }
        }
    }

    override fun onComplete() {
        CHProgress.dismiss()
    }

    override fun onError(e: Throwable) {
        when(e){
            is HttpException,is ConnectException,is UnknownHostException,is NetworkErrorException -> onFailure("无法连接到服务器,请稍候再试...")
            is InterruptedIOException,is TimeoutException -> onFailure("服务器超时,请稍候再试...")
            is JsonParseException,is JSONException,is ParseException -> onFailure("数据解析失败")
            else -> onFailure("无法连接到服务器,请稍候再试...")
        }
        onComplete()
    }
}