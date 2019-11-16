package tj.ajoibot.logistics.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.ajoibot.logistics.internal.interfaces.IStoredItemsRepository

class StoredItemViewModelFactory(
    private val storedItemsRepo: IStoredItemsRepository
    ) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StoredItemViewModel(storedItemsRepo) as T
    }
}