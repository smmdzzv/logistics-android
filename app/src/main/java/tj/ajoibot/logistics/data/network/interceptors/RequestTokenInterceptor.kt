package tj.ajoibot.logistics.data.network.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import tj.ajoibot.logistics.internal.interfaces.IRequestTokenInterceptor
import tj.ajoibot.logistics.internal.utils.SharedSettings

class RequestTokenInterceptor(private val settings: SharedSettings) : IRequestTokenInterceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest: Request

        val token = settings.getToken()

        if (!token.isNullOrBlank()) {
            newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()

            Log.d("auth", "Token attached $token")

            return chain.proceed(newRequest)
        }

        return chain.proceed(request)
    }
}