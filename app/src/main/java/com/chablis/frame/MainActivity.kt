package com.chablis.frame

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log.d
import android.view.View
import com.chablis.frame.base.rx.ApiClient
import com.chablis.frame.base.rx.CHObserver
import com.chablis.frame.databinding.ActivityMainBinding
import com.chablis.frame.model.TestModel
import com.chablis.frame.model.User
import com.chablis.frame.viewModel.FirstViewModel
import com.chablis.kotlinretrofit2rx.NetworkScheduler
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : RxAppCompatActivity() {
    var user:User?=null
    var model:FirstViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var binding= DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        model = ViewModelProviders.of(this).get(FirstViewModel::class.java)
        model?.getUser()!!.observe(this, Observer {
            binding.user=it
        })
        test()
    }

    fun test(){
        ApiClient.instance.service.test()
                .compose(NetworkScheduler.compose())
                .bindUntilEvent(this, ActivityEvent.DESTROY)
                .subscribe(object :CHObserver<List<TestModel>>(){
                    override fun onSuccess(s: List<TestModel>) {
                        d("AAA",s.toString())
                    }

                    override fun onFailure(s: String) {
                        d("AAA",s)
                    }

                })
    }

    fun clickView(v: View){
        when (v.id){
            tv.id -> model?.loadData()
        }
    }
}
