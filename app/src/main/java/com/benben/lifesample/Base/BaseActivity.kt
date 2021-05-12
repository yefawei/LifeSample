package com.benben.lifesample.Base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.benben.lifesample.lifecycle.ILifecycleOwner
import com.benben.lifesample.lifecycle.LifeState
import com.benben.lifesample.lifecycle.LifecycleProvider

/**
 * @Author:         BenBen
 * @CreateDate:     2021/5/12 21:26
 * @Description:
 */
open class BaseActivity : AppCompatActivity(), ILifecycleOwner {

    private val lifeProvider by lazy {
        LifecycleProvider()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifeProvider.makeLifeState(LifeState.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifeProvider.makeLifeState(LifeState.START)
    }

    override fun onResume() {
        super.onResume()
        lifeProvider.makeLifeState(LifeState.RESUME)
    }

    override fun onPause() {
        super.onPause()
        lifeProvider.makeLifeState(LifeState.PAUSE)
    }

    override fun onStop() {
        super.onStop()
        lifeProvider.makeLifeState(LifeState.STOP)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifeProvider.makeLifeState(LifeState.DESTORY)
    }

    override fun getLifecycleProvider(): LifecycleProvider {
        return lifeProvider
    }
}