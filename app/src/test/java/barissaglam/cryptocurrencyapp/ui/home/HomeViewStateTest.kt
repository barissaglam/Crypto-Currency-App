package barissaglam.cryptocurrencyapp.ui.home

import barissaglam.domain.model.Coin
import barissaglam.domain.model.CoinsData
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HomeViewStateTest {

    private val coinData = mockk<CoinsData>()
    private lateinit var homeViewState: HomeViewState

    @BeforeEach
    fun setUpBefore() {
        homeViewState = HomeViewState(coinData)
    }

    // When Then Return
    @ParameterizedTest(name = "given list:{0}, when called getItems(), then list size should be equal:{1}")
    @MethodSource("getItemsArguments")
    fun `getItems() size test`(given: List<Coin>, expected: Int) {
        // given
        every { coinData.coins } returns given

        // when
        val actualResult = homeViewState.getItems()

        // then
        assertThat(actualResult).hasSize(expected)
        assertThat(actualResult).isEqualTo(coinData.coins)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    companion object {
        @JvmStatic
        fun getItemsArguments() = listOf(
            Arguments.of(emptyList<Coin>(), 0),
            Arguments.of(
                listOf(
                    mockk<Coin>(),
                    mockk()
                ),
                2
            ),
            Arguments.of(
                listOf(
                    mockk<Coin>(),
                    mockk(),
                    mockk()
                ),
                3
            )
        )
    }
}
