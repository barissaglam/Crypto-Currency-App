package barissaglam.cryptocurrencyapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import barissaglam.core.view.BaseFragment
import barissaglam.cryptocurrencyapp.R
import barissaglam.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import barissaglam.cryptocurrencyapp.extensions.observe
import barissaglam.cryptocurrencyapp.ui.detail.data.DetailLoadingType
import barissaglam.cryptocurrencyapp.ui.detail.data.DetailLoadingType.PROGRESS
import barissaglam.cryptocurrencyapp.ui.detail.data.DetailLoadingType.SHIMMER
import barissaglam.domain.model.TimePeriod
import barissaglam.domain.model.TimePeriod.DAILY
import barissaglam.domain.model.TimePeriod.MONTHLY
import barissaglam.domain.model.TimePeriod.WEEKLY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment<FragmentCoinDetailBinding>(R.layout.fragment_coin_detail) {

    override val viewModel: CoinDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpClickListeners()
        setUpLineChart()
        observeViewModelFields()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            getCoinDetail(DAILY, SHIMMER)
        }
    }

    private fun observeViewModelFields() {
        with(viewModel) {
            observe(uiStateData) { uiState ->
                binding.uiState = uiState
                binding.executePendingBindings()
            }
            observe(viewStateData) { detailViewState ->
                binding.viewState = detailViewState
                binding.lineChart.data = detailViewState.getLineData(requireContext())
                binding.lineChart.invalidate()
            }
        }
    }

    private fun setUpClickListeners() {
        with(binding) {
            imageViewBack.setOnClickListener { activity?.onBackPressed() }
            chip24h.setOnClickListener { getCoinDetail(DAILY) }
            chip7d.setOnClickListener { getCoinDetail(WEEKLY) }
            chip30d.setOnClickListener { getCoinDetail(MONTHLY) }
        }
    }

    private fun getCoinDetail(timePeriod: TimePeriod, loadingType: DetailLoadingType = PROGRESS) {
        viewModel.getCoinDetail(timePeriod, loadingType)
    }

    private fun setUpLineChart() {
        binding.lineChart.apply {
            setDrawBorders(false)
            description.isEnabled = false
            isDragEnabled = false
            xAxis.isEnabled = false
            axisLeft.isEnabled = false
            axisRight.setDrawAxisLine(false)
            axisRight.textColor = ContextCompat.getColor(requireContext(), R.color.color_text_secondary)
            legend.isEnabled = false
            setTouchEnabled(false)
            setScaleEnabled(false)
            setDrawBorders(false)
        }
    }
}
