package tj.ajoibot.ajoibotlogistics.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.ajoibotlogistics.data.models.Result
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthentificationResponse
import tj.ajoibot.ajoibotlogistics.data.repositories.AuthRepository
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRemoteDataSource

class LoginViewModel(private val repository: AuthRepository): ViewModel() {

    private val _authorizeResponse = MutableLiveData<Result<AuthentificationResponse>>()
    val authorizeResponse: LiveData<Result<AuthentificationResponse>>
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