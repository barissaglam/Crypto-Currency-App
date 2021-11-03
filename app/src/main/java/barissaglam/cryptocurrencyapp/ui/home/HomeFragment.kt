package barissaglam.cryptocurrencyapp.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
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
import barissaglam.cryptocurrencyapp.ui.home.service.CoinsUpdateScope
import barissaglam.cryptocurrencyapp.ui.home.service.CoinsUpdateService
import barissaglam.cryptocurrencyapp.utils.BundleKeys.CoinDetail
import barissaglam.cryptocurrencyapp.utils.BundleKeys.CoinList
import barissaglam.data.model.uimodel.Coin
import barissaglam.data.model.uimodel.CoinsData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), HomeAdapterCallBack {

    override val viewModel: HomeViewModel by viewModels()
    private val coinsAdapter: HomeAdapter = HomeAdapter(this)
    private val updateCoinServiceIntent: Intent by lazy {
        Intent(context, CoinsUpdateService::class.java)
    }
    private val updateCoinsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.getParcelableExtra<CoinsData>(CoinList.KEY_COINS)?.let { coinsData ->
                viewModel.updateCoinsData(coinsData)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeViewModelFields()
    }

    private fun observeViewModelFields() {
        with(viewModel) {
            observe(layoutViewState) { layoutViewState ->
                binding.layoutViewState = layoutViewState
            }
            observe(viewState) { viewState ->
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

    override fun onResume() {
        context?.let { context ->
            with(context) {
                registerReceiver(updateCoinsReceiver, IntentFilter(CoinsUpdateScope.INTENT_ACTION))
                startService(updateCoinServiceIntent)
            }
        }
        super.onResume()
    }

    override fun onPause() {
        context?.let { context ->
            with(context) {
                unregisterReceiver(updateCoinsReceiver)
                stopService(updateCoinServiceIntent)
            }
        }
        super.onPause()
    }
}
