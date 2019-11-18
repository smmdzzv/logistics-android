package tj.ajoibot.logistics.ui.trips

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.internal.base.BaseViewModel
import tj.ajoibot.logistics.internal.extensions.playFailSound
import tj.ajoibot.logistics.internal.extensions.playSuccessSound
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository
import java.lang.Exception

class TripsViewModel(private val repo: ITripsRepository, private val context: Context) : BaseViewModel() {

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
                context.playSuccessSound()

            } catch (e: Exception) {
                Log.d("loading", "Failed to load item $e")
                mStatusMessage.postValue("Неудалось загрузить товар. Попробуйте еще раз")
                context.playFailSound()
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
                context.playSuccessSound()
            } catch (e: Exception) {
                Log.d("unloading", "Failed to unload item $e")
                mStatusMessage.postValue("Неудалось принять товар. Попробуйте еще раз")
                context.playFailSound()
            }
            mSendingRequest.postValue(false)
            Log.d("unloading", "Request for unload sent $tripId and $itemCode")
        }
    }

    fun transferItem(tripId: String, targetTripId: String, itemCode: String) {
        CoroutineScope(Dispatchers.IO).launch {
            Log.d("transfer", "Sending transfer request $tripId and $itemCode")
            mStatusMessage.postValue("Идет отправка данных на сервер...")
            mSendingRequest.postValue(true)
            try {
                val response = repo.transferItem(tripId, targetTripId, itemCode)
                Log.d("transfer", "Item transfer $response")
                mStatusMessage.postValue("Товар успешно переведен на другой рейс")
                context.playSuccessSound()
            } catch (e: Exception) {
                Log.d("transfer", "Failed to transfer item $e")
                mStatusMessage.postValue("Неудалось перевести товар на другой рейс. Попробуйте еще раз")
                context.playFailSound()
            }
            mSendingRequest.postValue(false)
            Log.d(
                "transfer",
                "Request for transfer completed. Trip: $tripId, target trip: $targetTripId and item code: $itemCode"
            )
        }
    }
}