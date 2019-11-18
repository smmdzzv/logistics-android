package tj.ajoibot.logistics.ui.barcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tj.ajoibot.logistics.internal.utils.Event

class BarCodeViewModel : ViewModel() {

    private val _decodedBarCode = MutableLiveData<Event<String>>()
    val decodedBarCode: LiveData<Event<String>>
        get() = _decodedBarCode

//    private val _sendingRequest = MutableLiveData<Boolean>()
//    val sendingRequest: LiveData<Boolean>
//        get() = _sendingRequest

    private val _scanningMode = MutableLiveData<Boolean>()
    val scanningMode: LiveData<Boolean>
        get() = _scanningMode

    //TODO remove from here
//    private val _statusMessage = MutableLiveData<String>()
//    val statusMessage: LiveData<String>
//        get() = _statusMessage

    /**
     *Sets decoded bar code to _decodedBarCode
     */
    fun onBarcodeDecoded(result: String) {
        if (scanningMode.value == true)
            _decodedBarCode.postValue(Event(result))
//        val state = sendingRequest.value
//        if (state == null || !state)
//            _decodedBarCode.postValue(result)
    }

    fun setScanningMode(state: Boolean) {
        _scanningMode.postValue(state)
    }
}
