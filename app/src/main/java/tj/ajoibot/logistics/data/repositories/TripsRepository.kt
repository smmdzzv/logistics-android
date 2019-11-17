package tj.ajoibot.logistics.data.repositories

import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.Deferred
import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository

class TripsRepository(private val remote: IRemoteDataSource) : ITripsRepository {

    override suspend fun getActiveTrips(): Result<List<ActiveTrip>> {
        return remote.getActiveTrips()
    }

    override suspend fun loadItem(tripId: String, itemCode: String) {
        return remote.loadItem(tripId, itemCode)
    }

    override suspend fun unloadItem(tripId: String, itemCode: String) {
        return remote.unloadItem(tripId, itemCode)
    }
}