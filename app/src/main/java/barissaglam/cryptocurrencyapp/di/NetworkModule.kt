package barissaglam.cryptocurrencyapp.di

import android.content.Context
import barissaglam.cryptocurrencyapp.BuildConfig
import barissaglam.data.api.RestApi
import barissaglam.data.di.ApiKeyInterceptor
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://api.coinranking.com/v2/"

    @Provides
    @Singleton
    fun provideNewsApi(
        okHttpBuilder: OkHttpClient.Builder
    ): RestApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpBuilder.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(RestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpBuilder(
        @ApplicationContext context: Context,
        apiKeyInterceptor: ApiKeyInterceptor
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {
            addInterceptor(apiKeyInterceptor)

            if (BuildConfig.DEBUG) {
                addInterceptor(ChuckInterceptor(context))
            }
        }
    }
}
