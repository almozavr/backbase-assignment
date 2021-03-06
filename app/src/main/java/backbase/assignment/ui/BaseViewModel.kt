package backbase.assignment.ui

import androidx.lifecycle.ViewModel
import backbase.assignment.LoggingExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

open class BaseViewModel : ViewModel(), CoroutineScope {
  override val coroutineContext = Job() + LoggingExceptionHandler
  override fun onCleared() = coroutineContext.cancel()
}
