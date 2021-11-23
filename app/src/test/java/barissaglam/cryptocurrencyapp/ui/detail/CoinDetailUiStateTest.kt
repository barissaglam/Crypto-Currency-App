package barissaglam.cryptocurrencyapp.ui.detail

import barissaglam.core.data.ApiResult
import barissaglam.cryptocurrencyapp.ui.detail.data.DetailLoadingType
import com.google.common.truth.Truth
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class CoinDetailUiStateTest {

    @ParameterizedTest(name = "given apiResult:{0} and loadingType:{1}, when called isShowShimmer(), then result should be equal to:{2}")
    @MethodSource("isShowShimmerArguments")
    fun testIsShowShimmer(
        givenApiResult: ApiResult<Any>,
        givenLoadingType: DetailLoadingType,
        expectedResult: Boolean
    ) {
        // given
        val viewState = CoinDetailUiState(givenApiResult, givenLoadingType)

        // when
        val actualResult = viewState.isShowShimmer()

        // then
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @ParameterizedTest(name = "given apiResult:{0} and loadingType:{1}, when called isShowProgress(), then result should be equal to:{2}")
    @MethodSource("isShowProgressArguments")
    fun testIsShowProgress(
        givenApiResult: ApiResult<Any>,
        givenLoadingType: DetailLoadingType,
        expectedResult: Boolean
    ) {
        // given
        val viewState = CoinDetailUiState(givenApiResult, givenLoadingType)

        // when
        val actualResult = viewState.isShowProgress()

        // then
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    @ParameterizedTest(name = "given apiResult:{0}, and loadingType:{1} when called isShowContent(), then result should be equal to:{2}")
    @MethodSource("isShowContentArguments")
    fun testIsShowContent(
        givenApiResult: ApiResult<Any>,
        givenLoadingType: DetailLoadingType,
        expectedResult: Boolean
    ) {
        // given
        val viewState = CoinDetailUiState(givenApiResult, givenLoadingType)

        // when
        val actualResult = viewState.isShowContent()

        // then
        Truth.assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        @JvmStatic
        fun isShowShimmerArguments() = listOf(
            Arguments.of(ApiResult.Loading, DetailLoadingType.SHIMMER, true),
            Arguments.of(ApiResult.Error(Throwable()), DetailLoadingType.SHIMMER, false),
            Arguments.of(ApiResult.Success(Unit), DetailLoadingType.SHIMMER, false),

            Arguments.of(ApiResult.Loading, DetailLoadingType.PROGRESS, false),
            Arguments.of(ApiResult.Error(Throwable()), DetailLoadingType.PROGRESS, false),
            Arguments.of(ApiResult.Success(Unit), DetailLoadingType.PROGRESS, false)
        )

        @JvmStatic
        fun isShowProgressArguments() = listOf(
            Arguments.of(ApiResult.Loading, DetailLoadingType.SHIMMER, false),
            Arguments.of(ApiResult.Error(Throwable()), DetailLoadingType.SHIMMER, false),
            Arguments.of(ApiResult.Success(Unit), DetailLoadingType.SHIMMER, false),

            Arguments.of(ApiResult.Loading, DetailLoadingType.PROGRESS, true),
            Arguments.of(ApiResult.Error(Throwable()), DetailLoadingType.PROGRESS, false),
            Arguments.of(ApiResult.Success(Unit), DetailLoadingType.PROGRESS, false)
        )

        @JvmStatic
        fun isShowContentArguments() = listOf(
            Arguments.of(ApiResult.Loading, DetailLoadingType.SHIMMER, false),
            Arguments.of(ApiResult.Error(Throwable()), DetailLoadingType.SHIMMER, false),
            Arguments.of(ApiResult.Success(Unit), DetailLoadingType.SHIMMER, true),

            Arguments.of(ApiResult.Loading, DetailLoadingType.PROGRESS, true),
            Arguments.of(ApiResult.Error(Throwable()), DetailLoadingType.PROGRESS, false),
            Arguments.of(ApiResult.Success(Unit), DetailLoadingType.PROGRESS, true)
        )
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}
