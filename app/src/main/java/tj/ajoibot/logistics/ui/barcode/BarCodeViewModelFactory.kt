package tj.ajoibot.logistics.ui.barcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BarCodeViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BarCodeViewModel() as T
    }
}