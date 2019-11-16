package tj.ajoibot.logistics.ui.item

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.data.models.response.AuthorizedUserResponse
import tj.ajoibot.logistics.data.models.response.StoredItem
import tj.ajoibot.logistics.data.repositories.AuthRepository
import tj.ajoibot.logistics.internal.interfaces.IStoredItemsRepository
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository

class StoredItemViewModel(
    private val storedItemsRepo: IStoredItemsRepository
) : ViewModel() {

    private val _storedItemResponse = MutableLiveData<Result<StoredItem>>()
    val storedItemResponse: LiveData<Result<StoredItem>>
        get() = _storedItemResponse


    fun getStoredItem(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = storedItemsRepo.getStoredItem(id)
            _storedItemResponse.postValue(response)

            Log.d("stored-item", "Get stored item by id $id response is $response")
        }
    }

}