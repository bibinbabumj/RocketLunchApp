package com.example.bibin.codereview.coroutine

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

object Coroutine {

    /**
     * This method to perform an IO operation on a background thread.
     */
    fun <T : Any> mIOperation(work: suspend (() -> T)) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                work()
            } catch (e: Exception) {
                Log.d("Exception", "mIOperation: "+ e.message.toString())
            }
        }

    /**
    * This method to perform an IO operation on a background thread and then update the UI on the main thread.
    */

    fun <T : Any> mIOThenMain(mWork: suspend (() -> T?), mCallback: suspend ((T?) -> Unit)) =
        CoroutineScope(Dispatchers.Main).launch {
            try {
                async(Dispatchers.IO) rt@{
                    return@rt mWork()
                }.await().let { mCallback(it) }
            } catch (e: Exception) {

            }
        }
}