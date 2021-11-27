package barissaglam.data.utils.dispatcher

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

class SuccessDispatcher(private val fileName: String) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        val mockResponse = MockResponse()

        return javaClass.classLoader?.getResourceAsStream(fileName)?.source()?.buffer()?.let { response ->
            mockResponse
                .setResponseCode(ResponseCode.OK)
                .setBody(response.readString(StandardCharsets.UTF_8))
        } ?: run {
            mockResponse
                .setResponseCode(ResponseCode.ERROR)
        }
    }
}
