package barissaglam.data.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        val request = builder.addHeader(API_KEY_NAME, API_KEY).build()

        return chain.proceed(request)
    }

    private companion object {
        private const val API_KEY_NAME = "x-access-token"
        private const val API_KEY = "coinranking7dd80d1faf68cb683f39dbfbbb374b97843bc9644c792af6"
    }
}
