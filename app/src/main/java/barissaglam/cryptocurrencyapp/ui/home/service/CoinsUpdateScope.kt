package barissaglam.cryptocurrencyapp.ui.home.service

import android.content.Context
import android.content.Intent
import barissaglam.core.extension.onSuccess
import barissaglam.cryptocurrencyapp.utils.BundleKeys.CoinList
import barissaglam.domain.usecase.CoinUseCase
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CoinsUpdateScope @Inject constructor(
    private val coinsUseCase: CoinUseCase,
    @ApplicationContext private val context: Context
) : CoroutineScope {

    private val job = SupervisorJob()
    private val singleThreadExecutor = Executors.newSingleThreadScheduledExecutor()
    private val broadcastIntent = Intent(INTENT_ACTION)

    override val coroutineContext = job + singleThreadExecutor.asCoroutineDispatcher()

    fun onStart() {
        singleThreadExecutor.scheduleAtFixedRate(
            {
                coinsUseCase(Unit)
                    .onSuccess { coinsData ->
                        broadcastIntent.putExtra(CoinList.KEY_COINS, coinsData)
                        context.sendBroadcast(broadcastIntent)
                    }.launchIn(this)
            },
            5, 10, TimeUnit.HOURS
        )
    }

    fun onStop() {
        job.cancel()
        singleThreadExecutor.shutdown()
    }

    companion object {
        const val INTENT_ACTION = "UpdateCoinList"
    }
}
