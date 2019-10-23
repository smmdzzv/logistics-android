package tj.ajoibot.ajoibotlogistics.data.network


import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthentificationResponse
import tj.ajoibot.ajoibotlogistics.internal.base.BaseDataSource
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRemoteDataSource
import tj.ajoibot.ajoibotlogistics.services.LogisticsService
import tj.ajoibot.ajoibotlogistics.data.models.Result

class RemoteDataSource(private val api: LogisticsService) : IRemoteDataSource, BaseDataSource() {

    override suspend fun authorize(credentials: Credentials): Result<AuthentificationResponse> {
        return getResult { api.authorize(credentials) }
    }
}