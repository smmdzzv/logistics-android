package tj.ajoibot.logistics.internal.interfaces

import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.StoredItem

interface IStoredItemsRepository {
    suspend fun getStoredItem(code: String): Result<StoredItem>
}