package tj.ajoibot.logistics.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.request.Credentials
import tj.ajoibot.logistics.data.models.response.AuthenticationResponse
import tj.ajoibot.logistics.data.repositories.AuthRepository

class LoginViewModel(private val repository: AuthRepository): ViewModel() {

    private val _authorizeResponse = MutableLiveData<Result<AuthenticationResponse>>()
    val authorizeResponse: LiveData<Result<AuthenticationResponse>>
        get() = _authorizeResponse

    /**
     *Return authentication token
     */
    fun authorize(credentials: Credentials){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.authorize(credentials)
            _authorizeResponse.postValue(response)

            Log.d("auth", "Token required $response")
        }
    }
}