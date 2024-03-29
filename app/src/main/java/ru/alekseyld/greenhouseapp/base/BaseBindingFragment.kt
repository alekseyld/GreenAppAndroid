package ru.alekseyld.greenhouseapp.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import io.reactivex.disposables.CompositeDisposable
import ru.alekseyld.greenhouseapp.viewmodel.base.BaseViewModel
import java.lang.reflect.ParameterizedType
import javax.inject.Inject



abstract class BaseBindingFragment<TViewModel : BaseViewModel, TViewBinding : ViewDataBinding> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var viewModel: TViewModel

    protected lateinit var binding : TViewBinding

    val inject by lazy { injectDependencies() }

    protected val disposable = CompositeDisposable()

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelType() =
        (this.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<TViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)

        inject

        viewModel = ViewModelProviders.of(this, viewModelFactory)[getViewModelType()]

        bindBaseViewModel()

        bindVariable()

        return binding.root
    }

    private fun bindBaseViewModel() {
        viewModel.disposable.observe(this, Observer {
            disposable.add(it!!)
        })

        viewModel.errorMessage.observe(this, Observer {
            it?.let { mes ->
                Toast.makeText(activity, mes, Toast.LENGTH_SHORT).show()
            }
        })
    }

    protected fun showMessage(mes: String) {
        Toast.makeText(context, mes, Toast.LENGTH_SHORT).show()
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    protected open fun hideKeyboard() {
        if (activity!!.currentFocus == null) return
        val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }

    protected abstract fun bindVariable()

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun injectDependencies()

    abstract fun onBackKeyPressed()

}