package barissaglam.cryptocurrencyapp.ui.home

import barissaglam.domain.model.Coin
import barissaglam.domain.model.CoinsData
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.junit.After
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HomeViewStateTest {

    private val coinData = mockk<CoinsData>()
    private lateinit var homeViewState: HomeViewState

    @BeforeEach
    fun setUpBefore() {
        homeViewState = HomeViewState(coinData)
    }

    // When Then Return
    @Test
    fun `when list is empty, when called getItems(), returns emptyList`() {
        val expectedResult = emptyList<Coin>()

        // given
        every { coinData.coins } returns emptyList()

        // when
        val actualResult = homeViewState.getItems()

        // then
        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}
