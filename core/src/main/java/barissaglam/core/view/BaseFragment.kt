package barissaglam.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseFragment<DB : ViewDataBinding>(@LayoutRes private val layoutRes: Int) : Fragment(layoutRes) {
    lateinit var binding: DB
    abstract val viewModel: BaseViewModel
    private val safeActivity = activity as? BaseActivity<*>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.error.flowWithLifecycle(lifecycle).collect { baseError ->
                (activity as? BaseActivity<*>)?.showError(baseError)
            }
        }
    }

    override fun onDestroy() {
        (activity as? BaseActivity<*>)?.hideError()
        super.onDestroy()
    }
}
