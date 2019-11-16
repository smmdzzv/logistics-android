package tj.ajoibot.logistics.data.repositories

import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.StoredItem
import tj.ajoibot.logistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.logistics.internal.interfaces.IStoredItemsRepository

class StoredItemsRepository(private val remoteDataSource: IRemoteDataSource) : IStoredItemsRepository {
    override suspend fun getStoredItem(id: String): Result<StoredItem> {
        return remoteDataSource.getStoredItem(id)
    }
}