package com.benben.lifesample

import android.app.Application
import android.os.Handler
import android.os.Looper

/**
 * @Author:         BenBen
 * @CreateDate:     2021/5/12 21:57
 * @Description:
 */
class MyApplication : Application() {

    companion object{
        val handler = Handler(Looper.getMainLooper())
    }
}