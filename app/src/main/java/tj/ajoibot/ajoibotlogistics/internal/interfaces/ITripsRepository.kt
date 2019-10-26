package tj.ajoibot.ajoibotlogistics.internal.interfaces

import tj.ajoibot.ajoibotlogistics.data.models.Result
import tj.ajoibot.ajoibotlogistics.data.models.response.ActiveTripsResponse

interface ITripsRepository {
    suspend fun getActiveTrips(): Result<List<ActiveTripsResponse>>
}