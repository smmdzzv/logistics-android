package tj.ajoibot.logistics.ui.trips

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.internal.base.BaseViewModel
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository
import java.lang.Exception

class TripsViewModel(private val repo: ITripsRepository) : BaseViewModel() {

    private val _targetTrip = MutableLiveData<ActiveTrip>()
    val targetTrip: LiveData<ActiveTrip>
        get() = _targetTrip

    fun setTargetTrip(trip: ActiveTrip?) {
        _targetTrip.postValue(trip)
    }

    fun loadItem(tripId: String, itemCode: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("loading", "Sending load item request $tripId and $itemCode")
            mStatusMessage.postValue("Идет отправка данных на сервер...")
            mSendingRequest.postValue(true)
            try {
                val response = repo.loadItem(tripId, itemCode)
                Log.d("loading", "Item loaded $response")
                mStatusMessage.postValue("Товар успешно загружен")
            } catch (e: Exception) {
                Log.d("loading", "Failed to load item $e")
                mStatusMessage.postValue("Неудалось загрузить товар. Попробуйте еще раз")
            }
            mSendingRequest.postValue(false)
            Log.d("loading", "Request for load sent $tripId and $itemCode")
        }
    }

    fun unloadItem(tripId: String, itemCode: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("unloading", "Sending unload request $tripId and $itemCode")
            mStatusMessage.postValue("Идет отправка данных на сервер...")
            mSendingRequest.postValue(true)
            try {
                val response = repo.unloadItem(tripId, itemCode)
                Log.d("unloading", "Item unloaded $response")
                mStatusMessage.postValue("Товар успешно принят")
            } catch (e: Exception) {
                Log.d("unloading", "Failed to unload item $e")
                mStatusMessage.postValue("Неудалось принять товар. Попробуйте еще раз")
            }
            mSendingRequest.postValue(false)
            Log.d("unloading", "Request for unload sent $tripId and $itemCode")
        }
    }

}