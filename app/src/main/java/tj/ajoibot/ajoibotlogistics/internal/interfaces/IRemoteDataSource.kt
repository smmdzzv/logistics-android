package tj.ajoibot.ajoibotlogistics.internal.interfaces

import tj.ajoibot.ajoibotlogistics.data.models.Result
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthentificationResponse

interface IRemoteDataSource {
    suspend fun authorize(credentials: Credentials): Result<AuthentificationResponse>
}