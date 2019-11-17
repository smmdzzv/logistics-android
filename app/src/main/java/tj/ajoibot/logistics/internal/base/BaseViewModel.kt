package tj.ajoibot.logistics.internal.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    protected val mSendingRequest = MutableLiveData<Boolean>()
    val sendingRequest: LiveData<Boolean>
        get() = mSendingRequest

    protected val mStatusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String>
        get() = mStatusMessage
}