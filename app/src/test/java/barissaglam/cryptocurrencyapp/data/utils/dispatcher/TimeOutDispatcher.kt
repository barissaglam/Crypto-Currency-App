package barissaglam.cryptocurrencyapp.data.utils.dispatcher

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import okhttp3.mockwebserver.SocketPolicy
import java.util.concurrent.TimeUnit

private const val PER_PERIOD = 1024L
private const val PERIOD = 2L

class TimeOutDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse()
            .setSocketPolicy(SocketPolicy.NO_RESPONSE)
            .throttleBody(PER_PERIOD, PERIOD, TimeUnit.SECONDS)
    }
}
