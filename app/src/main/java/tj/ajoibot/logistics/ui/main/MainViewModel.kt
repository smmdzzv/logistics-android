package tj.ajoibot.logistics.ui.main

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
import tj.ajoibot.logistics.data.repositories.AuthRepository
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository

class MainViewModel(
    private val authRepo: AuthRepository,
    private val tripsRepo: ITripsRepository
) : ViewModel() {

    private val _userResponse = MutableLiveData<Result<AuthorizedUserResponse>>()
    val userResponse: LiveData<Result<AuthorizedUserResponse>>
        get() = _userResponse

    private val _activeTripsResponse = MutableLiveData<Result<List<ActiveTrip>>>()
    val activeTrip: LiveData<Result<List<ActiveTrip>>>
        get() = _activeTripsResponse

    private var _selectedTrip: ActiveTrip? = null
    var selectedTrip: ActiveTrip?
        get() = _selectedTrip
        set(value) {
            _selectedTrip = value
        }

    /**
     *Return authorized user
     */
    fun getAuthorizedUser() {
        if (_userResponse.value == null)
            CoroutineScope(Dispatchers.IO).launch {
                val response = authRepo.getAuthorizedUser()
                _userResponse.postValue(response)

                Log.d("auth", "Authorized user data $response")
            }
    }

    fun getActiveTrips() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = tripsRepo.getActiveTrips()
            _activeTripsResponse.postValue(response)

            Log.d("auth", "Trips are refreshed $response")
        }
    }
}