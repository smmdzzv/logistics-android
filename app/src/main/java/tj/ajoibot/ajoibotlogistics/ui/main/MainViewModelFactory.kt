package tj.ajoibot.ajoibotlogistics.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.ajoibot.ajoibotlogistics.data.repositories.AuthRepository

class MainViewModelFactory(
    private val repository: AuthRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}