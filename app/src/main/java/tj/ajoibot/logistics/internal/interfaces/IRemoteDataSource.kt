package tj.ajoibot.logistics.internal.interfaces

import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.request.Credentials
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.data.models.response.AuthenticationResponse
import tj.ajoibot.logistics.data.models.response.AuthorizedUserResponse

interface IRemoteDataSource {
    suspend fun authorize(credentials: Credentials): Result<AuthenticationResponse>

    suspend fun getAuthorizedUser(): Result<AuthorizedUserResponse>

    suspend fun getActiveTrips(): Result<List<ActiveTrip>>
}