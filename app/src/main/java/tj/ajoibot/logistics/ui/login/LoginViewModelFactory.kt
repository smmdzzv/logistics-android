package tj.ajoibot.logistics.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.ajoibot.logistics.data.repositories.AuthRepository

class LoginViewModelFactory(
    private val repository: AuthRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository) as T
    }
}