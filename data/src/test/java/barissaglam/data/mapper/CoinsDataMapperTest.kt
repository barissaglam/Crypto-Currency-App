package barissaglam.data.mapper

import barissaglam.core.data.Mapper
import barissaglam.data.model.CoinsDataModel
import barissaglam.data.utils.RepositoryFactory
import barissaglam.data.utils.RepositoryNullFactory
import barissaglam.domain.model.CoinsData
import barissaglam.domain.model.Stats
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CoinsDataMapperTest {

    private lateinit var mapper: Mapper<CoinsDataModel, CoinsData>
    private val statsMapper = StatsMapper()
    private val coinMapper = CoinMapper()

    @BeforeEach
    fun setUpBefore() {
        mapper = CoinsDataMapper(statsMapper, coinMapper)
    }

    @Test
    fun `test null case`() {
        // given
        val coinsDataModel = RepositoryNullFactory.getCoinsDataModel()

        // when
        val actualResult = mapper.toMapUiModel(coinsDataModel)

        // then
        actualResult.apply {
            assertThat(this).isNotNull()
            assertThat(this).isInstanceOf(CoinsData::class.java)

            assertThat(stats).isInstanceOf(Stats::class.java)
            assertThat(coins).hasSize(0)
        }
    }

    @Test
    fun `test filled case`() {
        // given
        val coinsDataModel = RepositoryFactory.getCoinsDataModel()

        // when
        val actualResult = mapper.toMapUiModel(coinsDataModel)

        // then
        actualResult.apply {
            assertThat(this).isNotNull()
            assertThat(this).isInstanceOf(CoinsData::class.java)

            assertThat(stats).isInstanceOf(Stats::class.java)
            assertThat(coins).hasSize(6)
        }
    }
}
