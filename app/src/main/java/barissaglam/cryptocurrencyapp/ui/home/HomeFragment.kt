package barissaglam.cryptocurrencyapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import barissaglam.core.view.BaseFragment
import barissaglam.cryptocurrencyapp.R
import barissaglam.cryptocurrencyapp.databinding.FragmentHomeBinding
import barissaglam.cryptocurrencyapp.extensions.observe
import barissaglam.cryptocurrencyapp.ui.home.adapter.HomeAdapter
import barissaglam.cryptocurrencyapp.ui.home.adapter.HomeAdapterCallBack
import barissaglam.cryptocurrencyapp.utils.BundleKeys.CoinDetail
import barissaglam.domain.model.Coin
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), HomeAdapterCallBack {

    override val viewModel: HomeViewModel by viewModels()
    private val coinsAdapter: HomeAdapter = HomeAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModelFields()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) viewModel.getCoins()
    }

    private fun observeViewModelFields() {
        with(viewModel) {
            observe(uiStateData) { uiViewState ->
                binding.uiViewState = uiViewState
            }
            observe(viewStateData) { viewState ->
                coinsAdapter.submitList(viewState.getItems())
            }
        }
    }

    private fun setUpViews() {
        binding.recyclerViewCoins.adapter = coinsAdapter
    }

    override fun onItemClick(coin: Coin) {
        findNavController().navigate(
            R.id.action_homeFragment_to_coinDetailFragment,
            bundleOf(
                CoinDetail.KEY_UUID to coin.uuid
            )
        )
    }
}
