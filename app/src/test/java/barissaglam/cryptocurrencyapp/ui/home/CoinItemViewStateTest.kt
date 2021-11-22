package barissaglam.cryptocurrencyapp.ui.home

import android.content.Context
import android.os.Build.VERSION_CODES.Q
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import barissaglam.cryptocurrencyapp.R
import barissaglam.domain.model.Coin
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.math.BigDecimal

@Config(sdk = [Q])
@RunWith(AndroidJUnit4::class)
class CoinItemViewStateTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val coin = mockk<Coin>()

    private lateinit var coinItemViewState: CoinItemViewState

    @Before
    fun setUpBefore() {
        coinItemViewState = CoinItemViewState(coin)
    }

    @Test
    fun `given change is positive, when called getChangeIcon(), then result should be equal to ic_green`() {
        // given
        val expectedResult = context.getDrawable(R.drawable.ic_green)
        every { coin.change } returns 1.0

        // when
        val actualResult = coinItemViewState.getChangeIcon(context)

        // then
        assertThat(actualResult?.constantState).isEqualTo(expectedResult?.constantState)
    }

    @Test
    fun `given change is negative, when called getChangeIcon(), then result should be equal to ic_red`() {
        // given
        val expectedResult = context.getDrawable(R.drawable.ic_red)
        every { coin.change } returns -1.0

        // when
        val actualResult = coinItemViewState.getChangeIcon(context)

        // then
        assertThat(actualResult?.constantState).isEqualTo(expectedResult?.constantState)
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}

class CoinItemViewStateParameterizedTest {

    private val coin = mockk<Coin>()

    private lateinit var coinItemViewState: CoinItemViewState

    @BeforeEach
    fun setUpBefore() {
        coinItemViewState = CoinItemViewState(coin)
    }

    @ParameterizedTest(name = "given change:{0}, when called getChangeText(), then result should be equal:{1}")
    @MethodSource("getChangeTextArguments")
    fun testGetChangeText(given: Double, expected: String) {
        // given
        every { coin.change } returns given

        // when
        val actualResult = coinItemViewState.getChangeText()

        // then
        assertThat(actualResult).isEqualTo(expected)
    }

    @ParameterizedTest(name = "given price:{0}, when called getPriceText(), then result should be equal:{1}")
    @MethodSource("getPriceTextArguments")
    fun testGetPriceText(given: BigDecimal, expected: String) {
        // given
        every { coin.price } returns given

        // when
        val actualResult = coinItemViewState.getPriceText()

        // then
        assertThat(actualResult).isEqualTo(expected)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    companion object {
        @JvmStatic
        fun getChangeTextArguments() = listOf(
            Arguments.of(1.23432324324234, "1.23 %"),
            Arguments.of(20.324, "20.32 %"),
            Arguments.of(3.1, "3.10 %"),
            Arguments.of(3, "3.00 %"),
            Arguments.of(-1.23432324324234, "-1.23 %"),
            Arguments.of(-20.324, "-20.32 %"),
            Arguments.of(-3.1, "-3.10 %"),
            Arguments.of(-3, "-3.00 %")
        )

        @JvmStatic
        fun getPriceTextArguments() = listOf(
            Arguments.of(BigDecimal(1.23432324324234), "$1.23"),
            Arguments.of(BigDecimal(20.324), "$20.32"),
            Arguments.of(BigDecimal(3.1), "$3.10"),
            Arguments.of(BigDecimal(3), "$3.00")
        )
    }
}
