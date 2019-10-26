package tj.ajoibot.logistics.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.ajoibot.logistics.data.repositories.AuthRepository
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository

class MainViewModelFactory(
    private val authRepo: AuthRepository,
    private val tripsRepo: ITripsRepository
    ) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(authRepo, tripsRepo) as T
    }
}