package tj.ajoibot.logistics.internal.interfaces

import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.ActiveTrip

interface ITripsRepository {
    suspend fun getActiveTrips(): Result<List<ActiveTrip>>
}