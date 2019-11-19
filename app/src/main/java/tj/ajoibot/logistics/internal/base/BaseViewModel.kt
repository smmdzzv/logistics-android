package tj.ajoibot.logistics.internal.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.ajoibot.logistics.internal.utils.Event

open class BaseViewModel : ViewModel() {
    protected val mSendingRequest = MutableLiveData<Boolean>()
    val sendingRequest: LiveData<Boolean>
        get() = mSendingRequest

    protected val mStatusMessage = MutableLiveData<Event<String>>()
    val statusMessage: LiveData<Event<String>>
        get() = mStatusMessage

//    protected val mRequestSucceeded = MutableLiveData<Boolean>()
//    val requestSucceeded: LiveData<Boolean>
//        get() = mRequestSucceeded

    fun setStatusMessage(message: String) {
        mStatusMessage.postValue(Event(message))
    }
}