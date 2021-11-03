package barissaglam.cryptocurrencyapp.ui.home.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CoinsUpdateService @Inject constructor() : Service() {

    @Inject
    lateinit var coinsUpdateScope: CoinsUpdateScope

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        coinsUpdateScope.onStart()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        coinsUpdateScope.onStop()
    }
}
