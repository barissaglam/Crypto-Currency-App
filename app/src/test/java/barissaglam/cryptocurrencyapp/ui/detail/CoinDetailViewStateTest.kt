package barissaglam.cryptocurrencyapp.ui.detail

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import barissaglam.cryptocurrencyapp.R
import barissaglam.cryptocurrencyapp.ui.detail.data.TimePeriod
import barissaglam.cryptocurrencyapp.ui.home.CoinItemViewState
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

@Config(sdk = [Build.VERSION_CODES.Q])
@RunWith(AndroidJUnit4::class)
class CoinDetailViewStateTest {

    private val context = ApplicationProvider.getApplicationContext<Context>()

    private val coinData = mockk<Coin>()
    private lateinit var coinDetailViewState: CoinDetailViewState

    @Before
    fun setUpBefore() {
        coinDetailViewState = CoinDetailViewState(coinData)
    }

    @Test
    fun `given change is positive, when called getChartBackground(), then result should be equal to background_chart_up`() {
        //given
        val expected = context.getDrawable(R.drawable.background_chart_up)
        every { coinData.change } returns 1.0

        //when
        val result = coinDetailViewState.getChartBackground(context)

        //then
        assertThat(result?.constantState).isEqualTo(expected?.constantState)
    }

    @Test
    fun `given change is negative, when called getChartBackground(), then result should be equal to background_chart_down`() {
        //given
        val expected = context.getDrawable(R.drawable.background_chart_down)
        every { coinData.change } returns -1.0

        //when
        val result = coinDetailViewState.getChartBackground(context)

        //then
        assertThat(result?.constantState).isEqualTo(expected?.constantState)
    }

    @Test
    fun `given change is positive, when called getColor(), then result should be equal to up_green`() {
        //given
        val expected = context.getColor(R.color.up_green)
        every { coinData.change } returns 1.0

        //when
        val result = coinDetailViewState.getColor(context)

        //then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `given change is negative, when called getColor(), then result should be equal to down_red`() {
        //given
        val expected = context.getColor(R.color.down_red)
        every { coinData.change } returns -1.0

        //when
        val result = coinDetailViewState.getColor(context)

        //then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `given change is positive, when called getChangeIcon(), then result should be equal to ic_green`() {
        //given
        val expected = context.getDrawable(R.drawable.ic_green)
        every { coinData.change } returns 1.0

        //when
        val result = coinDetailViewState.getChangeIcon(context)

        //then
        assertThat(result?.constantState).isEqualTo(expected?.constantState)
    }

    @Test
    fun `given change is negative, when called getChangeIcon(), then result should be equal to ic_red`() {
        //given
        val expected = context.getDrawable(R.drawable.ic_red)
        every { coinData.change } returns - 1.0

        //when
        val result = coinDetailViewState.getChangeIcon(context)

        //then
        assertThat(result?.constantState).isEqualTo(expected?.constantState)
    }


    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}

class CoinDetailViewStateParameterizedTest {

    private val coin = mockk<Coin>()

    private lateinit var coinDetailViewState: CoinDetailViewState

    @BeforeEach
    fun setUpBefore() {
        coinDetailViewState = CoinDetailViewState(coin)
    }

    @ParameterizedTest(name = "given change:{0}, when called getChangeText(), then result should be equal:{1}")
    @MethodSource("getChangeTextArguments")
    fun testGetChangeText(given: Double, expected: String) {
        // given
        every { coin.change } returns given

        // when
        val actualResult = coinDetailViewState.getChangeText()

        // then
        assertThat(actualResult).isEqualTo(expected)
    }

    @ParameterizedTest(name = "given price:{0}, when called getPriceText(), then result should be equal:{1}")
    @MethodSource("getPriceTextArguments")
    fun testGetPriceText(given: BigDecimal, expected: String) {
        // given
        every { coin.price } returns given

        // when
        val actualResult = coinDetailViewState.getPriceText()

        // then
        assertThat(actualResult).isEqualTo(expected)
    }

    @ParameterizedTest(name = "given price:{0}, when called isChip24hChecked(), then result should be equal:{1}")
    @MethodSource("isChip24hCheckedArguments")
    fun testIsChip24hChecked(given: TimePeriod, expected: Boolean) {
        // given
        coinDetailViewState = CoinDetailViewState(coin,given)

        // when
        val actualResult = coinDetailViewState.isChip24hChecked()

        // then
        assertThat(actualResult).isEqualTo(expected)
    }

    @ParameterizedTest(name = "given price:{0}, when called isChip7dChecked(), then result should be equal:{1}")
    @MethodSource("isChip7dCheckedArguments")
    fun testIsChip7dChecked(given: TimePeriod, expected: Boolean) {
        // given
        coinDetailViewState = CoinDetailViewState(coin,given)

        // when
        val actualResult = coinDetailViewState.isChip7dChecked()

        // then
        assertThat(actualResult).isEqualTo(expected)
    }

    @ParameterizedTest(name = "given price:{0}, when called isChip30dChecked(), then result should be equal:{1}")
    @MethodSource("isChip30dCheckedArguments")
    fun testIsChip30dChecked(given: TimePeriod, expected: Boolean) {
        // given
        coinDetailViewState = CoinDetailViewState(coin,given)

        // when
        val actualResult = coinDetailViewState.isChip30dChecked()

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

        @JvmStatic
        fun isChip24hCheckedArguments() = listOf(
            Arguments.of(TimePeriod.DAILY, true),
            Arguments.of(TimePeriod.WEEKLY, false),
            Arguments.of(TimePeriod.MONTHLY, false)
        )

        @JvmStatic
        fun isChip7dCheckedArguments() = listOf(
            Arguments.of(TimePeriod.DAILY, false),
            Arguments.of(TimePeriod.WEEKLY, true),
            Arguments.of(TimePeriod.MONTHLY, false)
        )

        @JvmStatic
        fun isChip30dCheckedArguments() = listOf(
            Arguments.of(TimePeriod.DAILY, false),
            Arguments.of(TimePeriod.WEEKLY, false),
            Arguments.of(TimePeriod.MONTHLY, true)
        )
    }
}

