package com.aragoncodes.android_kotlin_mvvm_architecture.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProviders
import com.aragoncodes.android_kotlin_mvvm_architecture.BR
import com.aragoncodes.android_kotlin_mvvm_architecture.R
import com.aragoncodes.android_kotlin_mvvm_architecture.databinding.ActivityMainBinding
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.base.BaseActivity
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.login.LoginActivity
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), Main_.Navigator {

    // VARIABLES
    // ====================================================
    private lateinit var mMainViewModel: MainViewModel
    private lateinit var vBinding: ActivityMainBinding
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModel: MainViewModel
        get() {
            mMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
            return mMainViewModel
        }


    // FUNCTIONS
    // ====================================================
    // => Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = viewDataBinding!!
        mMainViewModel.navigator = this

        initView()

    }
    private fun initView() {
        initClick()
    }
    private fun initClick() {
        vBinding.bttLogout.setOnClickListener {
            logout()
        }
    }

    // => Primary
    override fun handleError(throwable: Throwable) {
        Timber.d("handleError")
    }

    override fun logout() {
        mMainViewModel.logout()
    }

    override fun openLoginActivity() {
        val intent = LoginActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    // Public Static
    // ====================================================
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}