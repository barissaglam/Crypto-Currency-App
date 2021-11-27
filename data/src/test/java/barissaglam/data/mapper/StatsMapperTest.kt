package barissaglam.data.mapper

import barissaglam.core.data.Mapper
import barissaglam.data.model.StatsModel
import barissaglam.data.utils.RepositoryFactory
import barissaglam.data.utils.RepositoryNullFactory
import barissaglam.domain.model.Stats
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class StatsMapperTest {

    private lateinit var mapper: Mapper<StatsModel?, Stats>

    @BeforeEach
    fun setUpBefore() {
        mapper = StatsMapper()
    }

    @Test
    fun `test null case`() {
        // given
        val dataModel = RepositoryNullFactory.getStatsModel()

        // when
        val actualResult = mapper.toMapUiModel(dataModel)

        // then
        assertThat(actualResult).isNotNull()
        assertThat(actualResult.total).isEqualTo(0)
        assertThat(actualResult.totalCoins).isEqualTo(0)
        assertThat(actualResult.totalMarkets).isEqualTo(0)
        assertThat(actualResult.totalExchanges).isEqualTo(0)
        assertThat(actualResult.totalMarketCap).isEqualTo(BigDecimal(0))
        assertThat(actualResult.total24hVolume).isEqualTo(BigDecimal(0))
        assertThat(actualResult).isInstanceOf(Stats::class.java)
    }

    @Test
    fun `test filled case`() {
        // given
        val dataModel = RepositoryFactory.getStatsModel()

        // when
        val actualResult = mapper.toMapUiModel(dataModel)

        // then
        assertThat(actualResult).isNotNull()
        assertThat(actualResult.total).isEqualTo(10)
        assertThat(actualResult.totalCoins).isEqualTo(50)
        assertThat(actualResult.totalMarkets).isEqualTo(500)
        assertThat(actualResult.totalExchanges).isEqualTo(250)
        assertThat(actualResult.totalMarketCap).isEqualTo(BigDecimal(758))
        assertThat(actualResult.total24hVolume).isEqualTo(BigDecimal(1312))
        assertThat(actualResult).isInstanceOf(Stats::class.java)
    }
}
