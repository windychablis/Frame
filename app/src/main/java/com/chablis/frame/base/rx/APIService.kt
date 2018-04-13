package com.chablis.frame.base.rx

import com.chablis.frame.base.model.BaseResult
import com.chablis.frame.model.TestModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by chablis on 2017/11/15.
 */
interface APIService {
    //首页3大板块
    @GET("constants/queryAllData")
    fun test(): Observable<BaseResult<List<TestModel>>>

}