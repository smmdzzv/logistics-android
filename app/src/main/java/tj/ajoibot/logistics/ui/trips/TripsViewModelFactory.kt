package tj.ajoibot.logistics.ui.trips

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository

class TripsViewModelFactory(
    private val repo: ITripsRepository,
    private val context: Context
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TripsViewModel(repo, context) as T
    }
}