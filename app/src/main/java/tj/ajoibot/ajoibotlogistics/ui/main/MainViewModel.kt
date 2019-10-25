package tj.ajoibot.ajoibotlogistics.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.ajoibotlogistics.data.models.Result
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthenticationResponse
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthorizedUserResponse
import tj.ajoibot.ajoibotlogistics.data.repositories.AuthRepository

class MainViewModel(private val repository: AuthRepository): ViewModel() {

    private val _userResponse = MutableLiveData<Result<AuthorizedUserResponse>>()
    val userResponse: LiveData<Result<AuthorizedUserResponse>>
        get() = _userResponse

    /**
     *Return authorized user
     */
    fun getAuthorizedUser(){
        if(_userResponse.value == null)
            CoroutineScope(Dispatchers.IO).launch {
                val response = repository.getAuthorizedUser()
                _userResponse.postValue(response)

                Log.d("auth", "Authorized user data $response")
            }
    }
}