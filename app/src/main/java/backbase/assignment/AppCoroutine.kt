package backbase.assignment

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.plus


val LoggingExceptionHandler = CoroutineExceptionHandler { _, t ->
  Log.w("App", "Coroutine crashed", t)
}

val AppScope = GlobalScope + LoggingExceptionHandler
