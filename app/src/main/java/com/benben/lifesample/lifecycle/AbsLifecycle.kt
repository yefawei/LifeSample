package com.benben.lifesample.lifecycle

abstract class AbsLifecycle : ILifeCycle {

    override fun onCreateState() {
    }

    override fun onStarState() {
    }

    override fun onResumeState() {
    }

    override fun onPauseState() {
    }

    override fun onStopState() {
    }

    override fun onDestoryState() {
    }

    abstract fun onViewLifeStateChange(state: LifeState)

}
