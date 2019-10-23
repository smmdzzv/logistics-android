package tj.ajoibot.ajoibotlogistics.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.data.models.response.AuthentificationResponse

interface LogisticsService {

    @POST("authorize")
    suspend fun authorize(@Body data: Credentials): Response<AuthentificationResponse>

    companion object {
        operator fun invoke(): LogisticsService {

            val httpClient = OkHttpClient.Builder()

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