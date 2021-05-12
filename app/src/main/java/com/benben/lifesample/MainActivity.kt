package com.benben.lifesample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.benben.lifesample.Base.BaseActivity
import com.benben.lifesample.lifecycle.AbsLifecycle
import com.benben.lifesample.lifecycle.LifeState

class MainActivity : BaseActivity() {

    private val mainPresenter by lazy {
        MainPresenter(this)
    }

    private val onCreateLifecycle = object : AbsLifecycle() {
        override fun onViewLifeStateChange(state: LifeState) {
            Log.e("onCreateLifecycle", state.name)
        }
    }

    private val onStartLifecycle = object : AbsLifecycle() {
        override fun onViewLifeStateChange(state: LifeState) {
            Log.e("onStartLifecycle", state.name)
        }
    }

    private val onResumeLifecycle = object : AbsLifecycle() {
        override fun onViewLifeStateChange(state: LifeState) {
            Log.e("onResumeLifecycle", state.name)
        }
    }

    private val onPauseLifecycle = object : AbsLifecycle() {
        override fun onViewLifeStateChange(state: LifeState) {
            Log.e("onPauseLifecycle", state.name)
        }
    }

    private val onStopLifecycle = object : AbsLifecycle() {
        override fun onViewLifeStateChange(state: LifeState) {
            Log.e("onStopLifecycle", state.name)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
        getLifecycleProvider().addLifeListener(onCreateLifecycle)
    }

    private fun initListener() {
        mainPresenter.name.addListener(this) {
            findViewById<TextView>(R.id.name).text = it
        }
    }

    fun change(view: View) {
        mainPresenter.changeName()
    }

    override fun onStart() {
        super.onStart()
        getLifecycleProvider().addLifeListener(onStartLifecycle)
    }

    override fun onResume() {
        super.onResume()
        getLifecycleProvider().addLifeListener(onResumeLifecycle)
    }

    override fun onPause() {
        super.onPause()
        getLifecycleProvider().addLifeListener(onPauseLifecycle)
    }

    override fun onStop() {
        super.onStop()
        getLifecycleProvider().addLifeListener(onStopLifecycle)
    }

    override fun onDestroy() {
        super.onDestroy()
        getLifecycleProvider().removeLifeListener(onCreateLifecycle)
        getLifecycleProvider().removeLifeListener(onStartLifecycle)
        getLifecycleProvider().removeLifeListener(onResumeLifecycle)
        getLifecycleProvider().removeLifeListener(onPauseLifecycle)
        getLifecycleProvider().removeLifeListener(onStopLifecycle)
    }
}