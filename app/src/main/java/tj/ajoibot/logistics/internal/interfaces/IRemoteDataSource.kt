package tj.ajoibot.logistics.internal.interfaces

import kotlinx.coroutines.CompletableJob
import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.request.Credentials
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.data.models.response.AuthenticationResponse
import tj.ajoibot.logistics.data.models.response.AuthorizedUserResponse
import tj.ajoibot.logistics.data.models.response.StoredItem

interface IRemoteDataSource {
    suspend fun authorize(credentials: Credentials): Result<AuthenticationResponse>

    suspend fun getAuthorizedUser(): Result<AuthorizedUserResponse>

    suspend fun getActiveTrips(): Result<List<ActiveTrip>>

    suspend fun loadItem(tripId: String, itemCode: String)

    suspend fun unloadItem(tripId: String, itemCode: String)

    suspend fun transferItem(tripId: String, targetTripId: String, itemCode: String)

    suspend fun getStoredItem(code: String): Result<StoredItem>
}