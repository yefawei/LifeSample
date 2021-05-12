package com.benben.lifesample

import android.os.Looper
import com.benben.lifesample.lifecycle.AbsLifecycle
import com.benben.lifesample.lifecycle.ILifecycleOwner
import com.benben.lifesample.lifecycle.LifeState
import com.benben.lifesample.lifecycle.LifecycleProvider

/**
 * @Author:         BenBen
 * @CreateDate:     2021/5/12 21:50
 * @Description:
 */
class DataListenContainer<T> {

    private val valueObservers = arrayListOf<(T?) -> Unit>()
    private val viewLifecycleProviders = hashMapOf<(T?) -> Unit, LifecycleProvider>()

    var value: T? = null
        set(value: T?) {
            field = value
            if (Looper.getMainLooper().thread == Thread.currentThread()) {
                valueObservers.forEach {
                    dispatchValue(it, value)
                }
            } else {
                MyApplication.handler.post {
                    valueObservers.forEach {
                        dispatchValue(it, value)
                    }
                }
            }
        }

    private fun dispatchValue(it: (T?) -> Unit, value: T?) {
        val viewModelProvider: LifecycleProvider? = viewLifecycleProviders[it]

        if (viewModelProvider != null && viewModelProvider.isAtLeast(LifeState.START)) {
            it.invoke(value)
        }
    }

    fun addListener(owner: ILifecycleOwner, valueObserver: (T?) -> Unit) {
        val lifecycleProvider = owner.getLifecycleProvider()
        viewLifecycleProviders[valueObserver] = lifecycleProvider

        val observerWrapper = ValueObserverWrapper(valueObserver)
        lifecycleProvider.addLifeListener(observerWrapper)
        if (!valueObservers.contains(valueObserver)) {
            valueObservers.add(valueObserver)
        }
    }

    private inner class ValueObserverWrapper(private val valueObserver: (T?) -> Unit) : AbsLifecycle() {

        override fun onViewLifeStateChange(state: LifeState) {
            if (state == LifeState.DESTORY) {
                viewLifecycleProviders.remove(valueObserver)
            }
        }

    }
}