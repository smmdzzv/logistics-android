package tj.ajoibot.logistics.ui.item

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.StoredItem
import tj.ajoibot.logistics.internal.base.BaseViewModel
import tj.ajoibot.logistics.internal.interfaces.IStoredItemsRepository

class StoredItemViewModel(
    private val storedItemsRepo: IStoredItemsRepository
) : BaseViewModel() {

    private val _storedItemResponse = MutableLiveData<Result<StoredItem>>()
    val storedItemResponse: LiveData<Result<StoredItem>>
        get() = _storedItemResponse


    fun getStoredItem(code: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = storedItemsRepo.getStoredItem(code)
            _storedItemResponse.postValue(response)

            Log.d("stored-item", "Get stored item by code $code response is $response")
        }
    }

}