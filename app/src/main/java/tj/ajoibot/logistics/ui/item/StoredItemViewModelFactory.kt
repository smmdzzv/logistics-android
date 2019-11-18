package tj.ajoibot.logistics.ui.item

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tj.ajoibot.logistics.internal.interfaces.IStoredItemsRepository

class StoredItemViewModelFactory(
    private val storedItemsRepo: IStoredItemsRepository,
    private val context: Context
    ) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StoredItemViewModel(storedItemsRepo, context) as T
    }
}