package com.aragoncodes.android_kotlin_mvvm_architecture.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.aragoncodes.android_kotlin_mvvm_architecture.R
import com.aragoncodes.android_kotlin_mvvm_architecture.BR
import com.aragoncodes.android_kotlin_mvvm_architecture.ViewModelProviderFactory
import com.aragoncodes.android_kotlin_mvvm_architecture.util.CommonUtils
import com.aragoncodes.android_kotlin_mvvm_architecture.util.NetworkUtils
import com.google.android.material.snackbar.Snackbar
import com.parse.ParseInstallation
import com.parse.ParseUser
import dagger.android.AndroidInjection
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(), Base_.View, BaseFragment.Callback {

    // VARIABLES
    // ====================================================
    @Inject
    internal lateinit var factory: ViewModelProviderFactory
    private var mProgressDialog: ProgressDialog? = null
    var viewDataBinding: T? = null
        private set
    private var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
     // abstract val bindingVariable: Int
    open val bindingVariable: Int
        get() = BR.viewModel
    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    override val isNetworkConnected: Boolean
        get() = NetworkUtils.isNetworkConnected(applicationContext)

    // FUNCTIONS
    // ====================================================
    // => LifeCycle
    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(tag: String) {

    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    // => UI
    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    override fun showMessage(message: String?) {
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showError(message: String?) {
        if (message != null) {
            showSnackBar(message);
        } else {
            showSnackBar(getString(R.string.system_unexpected_error));
        }
    }
    private fun showSnackBar(message: String) {
        Snackbar.make(
            findViewById(android.R.id.content),
            Html.fromHtml("<font color=\"#ffffff\">$message</font>"),
            Snackbar.LENGTH_SHORT)
            .show()
    }

    // => Binding & Injection
    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }

    // => Permissions
    @TargetApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    @TargetApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

}