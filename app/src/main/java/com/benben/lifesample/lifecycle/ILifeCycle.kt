package com.benben.lifesample.lifecycle

/**
 * @Author:         BenBen
 * @CreateDate:     2021/5/12 22:04
 * @Description:
 */
interface ILifeCycle {

    fun onCreateState()

    fun onStarState()

    fun onResumeState()

    fun onPauseState()

    fun onStopState()

    fun onDestoryState()
}