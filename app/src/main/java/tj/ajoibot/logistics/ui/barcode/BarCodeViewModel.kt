package tj.ajoibot.logistics.ui.barcode

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository
import java.lang.Exception

class BarCodeViewModel(private val repo: ITripsRepository) : ViewModel() {

    private val _decodedBarCode = MutableLiveData<String>()
    val decodedBarCode: LiveData<String>
        get() = _decodedBarCode

    private val _sendingRequest = MutableLiveData<Boolean>()
    val sendingRequest: LiveData<Boolean>
        get() = _sendingRequest

    /**
     *Sets decoded bar code to _decodedBarCode
     */
    fun onBarcodeDecoded(result: String) {
        val state = sendingRequest.value
        if(state == null || !state)
            _decodedBarCode.postValue(result)
    }

    fun unloadItem(tripId: String, itemCode: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("unloading", "Sending unload request $tripId and $itemCode")
            _sendingRequest.postValue(true)
            try {
                val response = repo.unloadItem(tripId, itemCode)
                Log.d("unloading", "Item unloaded $response")
            } catch (e: Exception) {
                Log.d("unloading", "Failed to unload item $e")
            }
            _sendingRequest.postValue(false)
            Log.d("unloading", "Request for unload sent $tripId and $itemCode")
        }
    }

}
