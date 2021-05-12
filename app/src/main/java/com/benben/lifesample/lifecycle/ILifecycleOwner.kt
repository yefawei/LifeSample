package com.benben.lifesample.lifecycle

/**
 * @Author:         BenBen
 * @CreateDate:     2021/5/12 21:25
 * @Description:
 */
interface ILifecycleOwner {
    fun getLifecycleProvider(): LifecycleProvider
}