package barissaglam.cryptocurrencyapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import barissaglam.core.view.BaseFragment
import barissaglam.cryptocurrencyapp.R
import barissaglam.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import barissaglam.cryptocurrencyapp.extensions.observe
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
            observe(layoutViewState) { layoutViewState ->
                binding.layoutViewState = layoutViewState
            }
            observe(viewState) { detailViewState ->
                binding.viewState = detailViewState
                binding.lineChart.data = detailViewState.getLineData(requireContext())
            }
        }
    }

    private fun setUpClickListeners() {
        binding.imageViewBack.setOnClickListener { activity?.onBackPressed() }
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
