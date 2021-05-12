package com.benben.lifesample.lifecycle

class LifecycleProvider {

    private var currentLifeState = LifeState.DESTORY

    private val lifecycleListener = arrayListOf<AbsLifecycle>()

    private val lifecycleMap = mutableMapOf<AbsLifecycle, LifeState>()

    fun addLifeListener(listener: AbsLifecycle) {
        if (lifecycleListener.contains(listener)) {
            return
        }
        lifecycleListener.add(listener)
        when (currentLifeState) {
            LifeState.CREATE, LifeState.STOP -> {
                listener.onViewLifeStateChange(LifeState.CREATE)
            }
            LifeState.PAUSE, LifeState.START -> {
                listener.onViewLifeStateChange(LifeState.CREATE)
                listener.onViewLifeStateChange(LifeState.START)
            }
            LifeState.RESUME, LifeState.DESTORY -> {
                listener.onViewLifeStateChange(LifeState.CREATE)
                listener.onViewLifeStateChange(LifeState.START)
                listener.onViewLifeStateChange(LifeState.RESUME)
            }
        }
    }

    fun removeLifeListener(listener: AbsLifecycle) {
        lifecycleListener.remove(listener)
    }

    fun makeLifeState(state: LifeState) {
        currentLifeState = state
        lifecycleListener.forEach {
            it.onViewLifeStateChange(state)
        }
        when (state) {
            LifeState.CREATE -> {
                dispatchCreateState()
            }
            LifeState.START -> {
                dispatchStartState()
            }
            LifeState.RESUME -> {
                dispatchResumeState()
            }
            LifeState.PAUSE -> {
                dispatchPauseState()
            }
            LifeState.STOP -> {
                dispatchStopState()
            }
            LifeState.DESTORY -> {
                dispatchDestoryState()
            }
        }
    }

    private fun dispatchCreateState() {
        lifecycleListener.forEach {
            it.onCreateState()
        }
    }

    private fun dispatchStartState() {
        lifecycleListener.forEach {
            it.onStarState()
        }
    }

    private fun dispatchResumeState() {
        lifecycleListener.forEach {
            it.onResumeState()
        }
    }

    private fun dispatchPauseState() {
        lifecycleListener.forEach {
            it.onResumeState()
        }
    }

    private fun dispatchStopState() {
        lifecycleListener.forEach {
            it.onStopState()
        }
    }

    private fun dispatchDestoryState() {
        lifecycleListener.forEach {
            it.onDestoryState()
        }
    }

    fun isAtLeast(state: LifeState): Boolean {
        return currentLifeState >= state
    }
}
