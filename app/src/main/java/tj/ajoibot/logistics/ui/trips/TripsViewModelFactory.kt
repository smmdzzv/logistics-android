package tj.ajoibot.logistics.ui.trips

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository

class TripsViewModelFactory(
    private val repo: ITripsRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TripsViewModel(repo) as T
    }
}