package tj.ajoibot.ajoibotlogistics.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.models.response.ActiveTripsResponse
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthenticationResponse
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthorizedUserResponse
import tj.ajoibot.ajoibotlogistics.internal.interfaces.IRequestTokenInterceptor

interface LogisticsService {

    @POST("authorize")
    suspend fun authorize(@Body data: Credentials): Response<AuthenticationResponse>

    @GET("user")
    suspend fun getAuthorizedUser(): Response<AuthorizedUserResponse>

    @GET("trips")
    suspend fun getActiveTrips(): Response<List<ActiveTripsResponse>>

    companion object {
        operator fun invoke(tokenInterceptor: IRequestTokenInterceptor): LogisticsService {

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(tokenInterceptor)

            val client = httpClient.build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .baseUrl("http://10.0.2.2:8000/api/")
                .client(client)
                .build()

            return retrofit.create(LogisticsService::class.java)
        }
    }
}