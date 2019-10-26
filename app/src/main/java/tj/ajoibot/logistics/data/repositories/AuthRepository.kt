package tj.ajoibot.logistics.data.repositories

import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.request.Credentials
import tj.ajoibot.logistics.data.models.response.AuthenticationResponse
import tj.ajoibot.logistics.data.models.response.AuthorizedUserResponse
import tj.ajoibot.logistics.internal.interfaces.IRemoteDataSource

class AuthRepository(private val remote: IRemoteDataSource){
    suspend fun authorize(credentials: Credentials): Result<AuthenticationResponse> {
        return remote.authorize(credentials)
    }

    suspend fun getAuthorizedUser(): Result<AuthorizedUserResponse>{
        return remote.getAuthorizedUser()
    }
}