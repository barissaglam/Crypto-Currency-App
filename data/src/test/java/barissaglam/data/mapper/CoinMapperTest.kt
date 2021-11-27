package barissaglam.data.mapper

import barissaglam.core.data.Mapper
import barissaglam.data.model.CoinModel
import barissaglam.data.utils.RepositoryFactory
import barissaglam.data.utils.RepositoryNullFactory
import barissaglam.domain.model.Coin
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class CoinMapperTest {

    private lateinit var mapper: Mapper<CoinModel?, Coin>

    @BeforeEach
    fun setUpBefore() {
        mapper = CoinMapper()
    }

    @Test
    fun `test null case`() {
        // given
        val coinModel = RepositoryNullFactory.getCoinModel()

        // when
        val actualResult = mapper.toMapUiModel(coinModel)

        // then
        actualResult.apply {
            assertThat(this).isNotNull()
            assertThat(uuid).isEmpty()
            assertThat(symbol).isEmpty()
            assertThat(name).isEmpty()
            assertThat(description).isEmpty()
            assertThat(color).isEmpty()
            assertThat(iconUrl).isEmpty()
            assertThat(marketCap).isEqualTo(BigDecimal(0))
            assertThat(price).isEqualTo(BigDecimal(0))
            assertThat(btcPrice).isEqualTo(BigDecimal(0))
            assertThat(listedAt).isEqualTo(0)
            assertThat(change).isEqualTo(0)
            assertThat(rank).isEqualTo(0)
            assertThat(sparkline).hasSize(0)
            assertThat(coinRankingUrl).isEmpty()
            assertThat(volume24h).isEqualTo(BigDecimal(0))
            assertThat(this).isInstanceOf(Coin::class.java)

            supply.apply {
                assertThat(total).isEqualTo(BigDecimal(0))
                assertThat(circulating).isEqualTo(BigDecimal(0))
                assertThat(confirmed).isFalse()
            }

            allTimeHigh.apply {
                assertThat(price).isEqualTo(BigDecimal(0))
                assertThat(timestamp).isEqualTo(0)
            }
        }
    }

    @Test
    fun `test filled case`() {
        // given
        val coinModel = RepositoryFactory.getCoinModel()

        // when
        val actualResult = mapper.toMapUiModel(coinModel)

        // then
        actualResult.apply {
            assertThat(this).isNotNull()
            assertThat(uuid).isEqualTo("123")
            assertThat(symbol).isEqualTo("BTC")
            assertThat(name).isEqualTo("Bitcoin")
            assertThat(description).isEqualTo("Desc Bitcoin")
            assertThat(color).isEqualTo("#DFDFDF")
            assertThat(iconUrl).isEqualTo("http://test.icon.url.png")
            assertThat(marketCap).isEqualTo(BigDecimal(31243))
            assertThat(price).isEqualTo(BigDecimal(54654654))
            assertThat(btcPrice).isEqualTo(BigDecimal(345435))
            assertThat(listedAt).isEqualTo(1232432)
            assertThat(change).isEqualTo(234)
            assertThat(rank).isEqualTo(345345)
            assertThat(sparkline).hasSize(3)
            assertThat(sparkline).containsExactly(BigDecimal(213), BigDecimal(32423), BigDecimal(56756))
            assertThat(coinRankingUrl).isEqualTo("http://coin.ranking.url")
            assertThat(volume24h).isEqualTo(BigDecimal(234324324))
            assertThat(this).isInstanceOf(Coin::class.java)

            supply.apply {
                assertThat(total).isEqualTo(BigDecimal(132132))
                assertThat(circulating).isEqualTo(BigDecimal(2321312))
                assertThat(confirmed).isTrue()
            }

            allTimeHigh.apply {
                assertThat(price).isEqualTo(BigDecimal(32324))
                assertThat(timestamp).isEqualTo(213213)
            }
        }
    }
}
