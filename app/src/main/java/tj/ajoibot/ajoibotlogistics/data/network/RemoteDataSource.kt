package tj.ajoibot.ajoibotlogistics.data.network


import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthenticationResponse
import tj.ajoibot.ajoibotlogistics.internal.base.BaseDataSource
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.ajoibotlogistics.services.LogisticsService
import tj.ajoibot.ajoibotlogistics.data.models.Result
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthorizedUserResponse

class RemoteDataSource(private val api: LogisticsService) : IRemoteDataSource, BaseDataSource() {
    override suspend fun getAuthorizedUser(): Result<AuthorizedUserResponse> {
        return getResult { api.getAuthorizedUser() }
    }

    override suspend fun authorize(credentials: Credentials): Result<AuthenticationResponse> {
        return getResult { api.authorize(credentials) }
    }
}