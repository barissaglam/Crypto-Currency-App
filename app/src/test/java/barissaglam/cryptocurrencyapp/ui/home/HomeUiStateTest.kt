package barissaglam.cryptocurrencyapp.ui.home

import barissaglam.core.data.ApiResult
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class HomeUiStateTest {

    @ParameterizedTest(name = "given apiResult:{0}, when called isShowShimmer(), return {1}")
    @MethodSource("isShowShimmerArguments")
    fun testIsShowShimmer(givenApiResult: ApiResult<Any>, expectedResult: Boolean) {
        // given
        val viewState = HomeUiState(givenApiResult)

        // when
        val actualResult = viewState.isShowShimmer()

        // then
        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @ParameterizedTest(name = "given apiResult:{0}, when called isShowContent(), return {1}")
    @MethodSource("isShowContentArguments")
    fun testIsShowContent(givenApiResult: ApiResult<Any>, expectedResult: Boolean) {
        // given
        val viewState = HomeUiState(givenApiResult)

        // when
        val actualResult = viewState.isShowContent()

        // then
        assertThat(actualResult).isEqualTo(expectedResult)
    }

    companion object {
        @JvmStatic
        fun isShowShimmerArguments() = listOf(
            Arguments.of(ApiResult.Loading, true),
            Arguments.of(ApiResult.Error(Throwable()), false),
            Arguments.of(ApiResult.Success(Unit), false)
        )

        @JvmStatic
        fun isShowContentArguments() = listOf(
            Arguments.of(ApiResult.Loading, false),
            Arguments.of(ApiResult.Error(Throwable()), false),
            Arguments.of(ApiResult.Success(Unit), true)
        )
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }
}
