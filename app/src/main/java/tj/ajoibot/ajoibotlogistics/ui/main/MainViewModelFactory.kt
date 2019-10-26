package tj.ajoibot.ajoibotlogistics.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.ajoibot.ajoibotlogistics.data.repositories.AuthRepository
import tj.ajoibot.ajoibotlogistics.internal.interfaces.ITripsRepository

class MainViewModelFactory(
    private val authRepo: AuthRepository,
    private val tripsRepo: ITripsRepository
    ) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(authRepo, tripsRepo) as T
    }
}