package barissaglam.data.di

import barissaglam.data.repository.CoinDetailRepositoryImpl
import barissaglam.data.repository.CoinRepositoryImpl
import barissaglam.domain.repository.CoinDetailRepository
import barissaglam.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindCoinRepository(repositoryImpl: CoinRepositoryImpl): CoinRepository

    @Singleton
    @Binds
    abstract fun bindCoinDetailRepository(repositoryImpl: CoinDetailRepositoryImpl): CoinDetailRepository
}
