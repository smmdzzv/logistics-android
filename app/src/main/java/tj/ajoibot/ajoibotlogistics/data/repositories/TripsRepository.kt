package tj.ajoibot.ajoibotlogistics.data.repositories

import tj.ajoibot.ajoibotlogistics.data.models.Result
import tj.ajoibot.ajoibotlogistics.data.models.response.ActiveTripsResponse
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.ajoibotlogistics.internal.interfaces.ITripsRepository

class TripsRepository(private val remote: IRemoteDataSource) : ITripsRepository {

    override suspend fun getActiveTrips(): Result<List<ActiveTripsResponse>> {
        return remote.getActiveTrips()
    }
}