package tj.ajoibot.logistics.data.network


import tj.ajoibot.logistics.data.models.request.Credentials
import tj.ajoibot.logistics.data.models.response.AuthenticationResponse
import tj.ajoibot.logistics.internal.base.BaseDataSource
import tj.ajoibot.logistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.logistics.services.LogisticsService
import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.data.models.response.AuthorizedUserResponse

class RemoteDataSource(private val api: LogisticsService) : IRemoteDataSource, BaseDataSource() {
    override suspend fun getAuthorizedUser(): Result<AuthorizedUserResponse> {
        return getResult { api.getAuthorizedUser() }
    }

    override suspend fun authorize(credentials: Credentials): Result<AuthenticationResponse> {
        return getResult { api.authorize(credentials) }
    }

    override suspend fun getActiveTrips(): Result<List<ActiveTrip>> {
        return getResult{ api.getActiveTrips()}
    }
}