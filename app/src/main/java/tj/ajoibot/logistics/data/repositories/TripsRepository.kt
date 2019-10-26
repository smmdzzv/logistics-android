package tj.ajoibot.logistics.data.repositories

import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.ActiveTripsResponse
import tj.ajoibot.logistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.logistics.internal.interfaces.ITripsRepository

class TripsRepository(private val remote: IRemoteDataSource) : ITripsRepository {

    override suspend fun getActiveTrips(): Result<List<ActiveTripsResponse>> {
        return remote.getActiveTrips()
    }
}