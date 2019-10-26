package tj.ajoibot.logistics.ui.barcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.ajoibot.logistics.data.repositories.AuthRepository
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository

class BarCodeViewModelFactory(
    private val tripsRepo: ITripsRepository
    ) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BarCodeViewModel(tripsRepo) as T
    }
}