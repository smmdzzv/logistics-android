package tj.ajoibot.ajoibotlogistics.internal.interfaces

import tj.ajoibot.ajoibotlogistics.data.models.Result
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthenticationResponse
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthorizedUserResponse

interface IRemoteDataSource {
    suspend fun authorize(credentials: Credentials): Result<AuthenticationResponse>

    suspend fun getAuthorizedUser(): Result<AuthorizedUserResponse>
}