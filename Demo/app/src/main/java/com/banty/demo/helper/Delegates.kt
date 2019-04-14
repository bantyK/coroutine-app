package com.banty.demo.helper

import kotlinx.coroutines.*

/**
 * Created by Banty on 2019-04-14.
 */


fun <T> deferredLazy(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}