package tj.ajoibot.logistics.internal.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel : ViewModel() {
    private val _sendingRequest = MutableLiveData<Boolean>()
    val sendingRequest: LiveData<Boolean>
        get() = _sendingRequest
}