package com.benben.lifesample

import android.util.Log
import com.benben.lifesample.DataListenContainer
import com.benben.lifesample.lifecycle.AbsLifecycle
import com.benben.lifesample.lifecycle.ILifecycleOwner
import com.benben.lifesample.lifecycle.LifeState

/**
 * @Author:         BenBen
 * @CreateDate:     2021/5/12 21:45
 * @Description:
 */
class MainPresenter(owner: ILifecycleOwner) {

    private val viewLifeImpl by lazy {
        ViewLifeImpl()
    }

    val name = DataListenContainer<String>()

    private var count = 0

    init {
        owner.getLifecycleProvider().addLifeListener(viewLifeImpl)
    }

    fun changeName() {
        name.value = "name: $count"
        count++
    }

    private inner class ViewLifeImpl : AbsLifecycle() {
        override fun onViewLifeStateChange(state: LifeState) {
            Log.e("ViewLifeImpl", state.name)
        }
    }
}