package tj.ajoibot.logistics.ui.item

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.StoredItem
import tj.ajoibot.logistics.internal.base.BaseViewModel
import tj.ajoibot.logistics.internal.extensions.playFailSound
import tj.ajoibot.logistics.internal.extensions.playSuccessSound
import tj.ajoibot.logistics.internal.interfaces.IStoredItemsRepository
import java.lang.Exception

class StoredItemViewModel(
    private val storedItemsRepo: IStoredItemsRepository,
    private val context: Context
) : BaseViewModel() {

    private val _storedItemResponse = MutableLiveData<Result<StoredItem>>()
    val storedItemResponse: LiveData<Result<StoredItem>>
        get() = _storedItemResponse


    fun getStoredItem(code: String) {
        if (sendingRequest.value != null && sendingRequest.value == true)
            return
        CoroutineScope(Dispatchers.IO).launch {
            mSendingRequest.postValue(true)
            try {
                val response = storedItemsRepo.getStoredItem(code)
                _storedItemResponse.postValue(response)
                Log.d("stored-item", "Get stored item by code $code response is $response")
                context.playSuccessSound()
            } catch (e: Exception) {
                context.playFailSound()
                Log.d("stored-item", "Failed to get stored item be code $code. Exception $e")
            }

            mSendingRequest.postValue(false)
        }
    }

}