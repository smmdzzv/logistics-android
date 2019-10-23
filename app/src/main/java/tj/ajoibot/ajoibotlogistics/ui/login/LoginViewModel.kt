package tj.ajoibot.ajoibotlogistics.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.repositories.AuthRepository
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRemoteDataSource

class LoginViewModel(private val repository: AuthRepository): ViewModel() {

    /**
     *Return authentication token
     */
    fun authorize(credentials: Credentials){
        Log.d("auth", "Credentials $credentials")
        CoroutineScope(Dispatchers.IO).launch {
            val result = repository.authorize(credentials)

            Log.d("auth", "Token required $result")
        }
    }
}