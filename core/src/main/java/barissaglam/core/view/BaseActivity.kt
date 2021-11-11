package barissaglam.core.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.allViews
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import barissaglam.core.R
import barissaglam.core.databinding.ActivityBaseBinding
import barissaglam.core.databinding.LayoutErrorBinding
import barissaglam.core.extension.getErrorMessage
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseActivity<DB : ViewDataBinding>(@LayoutRes private val layoutRes: Int) : AppCompatActivity() {
    abstract val viewModel: BaseViewModel?
    lateinit var binding: DB

    private val rootBinding: ActivityBaseBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_base)
    }

    private val errorBinding: LayoutErrorBinding by lazy {
        LayoutErrorBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            layoutInflater,
            layoutRes,
            rootBinding.content,
            true
        )
        binding.lifecycleOwner = this
        rootBinding.lifecycleOwner = this

        lifecycleScope.launch {
            viewModel?.error?.flowWithLifecycle(lifecycle)?.collect { baseError ->
                showError(baseError)
            }
        }
    }

    fun showError(baseError: BaseViewModel.BaseError) {
        errorBinding.apply {
            rootBinding.error.addView(root)
            errorText.text = baseError.throwable.getErrorMessage(root.context)
            retry.setOnClickListener {
                baseError.retryFunc()
                hideError()
            }
        }
    }

    fun hideError() {
        with(rootBinding.error) {
            if (allViews.count() != 0) {
                removeView(errorBinding.root)
            }
        }
    }
}
