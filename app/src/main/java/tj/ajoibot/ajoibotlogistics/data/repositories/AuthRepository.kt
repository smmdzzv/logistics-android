package tj.ajoibot.ajoibotlogistics.data.repositories

import tj.ajoibot.ajoibotlogistics.data.models.Result
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthenticationResponse
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthorizedUserResponse
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRemoteDataSource

class AuthRepository(private val remote: IRemoteDataSource){
    suspend fun authorize(credentials: Credentials): Result<AuthenticationResponse> {
        return remote.authorize(credentials)
    }

    suspend fun getAuthorizedUser(): Result<AuthorizedUserResponse>{
        return remote.getAuthorizedUser()
    }
}