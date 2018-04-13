package com.chablis.frame.base.rx

import com.chablis.frame.base.model.URLs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by chablis on 2017/11/15.
 */
class ApiClient private constructor() {
    lateinit var service: APIService

    private object Holder {
        val INSTANCE = ApiClient()
    }

    companion object {
        val instance by lazy { Holder.INSTANCE }
    }

    fun init() {  //在Application的onCreate中调用一次即可
        val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                //输入http连接时的log，也可添加更多的Interceptor
                .addInterceptor(HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.NONE
                ))
                .addInterceptor {
                    val original = it.request()
                    val request = original.newBuilder()
                            .header("User-Agent", "Android")
                            .method(original.method(), original.body())
                            .build()
                    it.proceed(request)
                }
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(URLs.BASE_URL)   //本文以GitHub API为例
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        service = retrofit.create(APIService::class.java)
    }

/*    private inner class HttpResultFunc<T> : Function<BaseResult<T>, T> {

        @Throws(Exception::class)
        override fun apply(tbase: BaseResult<T>): T? {
            return tbase.data
        }
    }*/
}
