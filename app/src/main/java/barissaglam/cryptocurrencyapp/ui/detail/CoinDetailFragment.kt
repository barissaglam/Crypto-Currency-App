package barissaglam.cryptocurrencyapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import barissaglam.core.view.BaseFragment
import barissaglam.cryptocurrencyapp.R
import barissaglam.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import barissaglam.cryptocurrencyapp.extensions.observe
import barissaglam.cryptocurrencyapp.ui.detail.TimePeriod.DAILY
import barissaglam.cryptocurrencyapp.ui.detail.TimePeriod.MONTHLY
import barissaglam.cryptocurrencyapp.ui.detail.TimePeriod.WEEKLY
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

    private fun observeViewModelFields() {
        with(viewModel) {
            observe(uiViewStateData) { uiViewState ->
                binding.uiViewState = uiViewState
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

    private fun getCoinDetail(timePeriod: TimePeriod) {
        viewModel.getCoinDetail(timePeriod, DetailLoadingType.Progress)
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
