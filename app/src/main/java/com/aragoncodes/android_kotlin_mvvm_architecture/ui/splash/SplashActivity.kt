package com.aragoncodes.android_kotlin_mvvm_architecture.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.aragoncodes.android_kotlin_mvvm_architecture.BR
import com.aragoncodes.android_kotlin_mvvm_architecture.R
import com.aragoncodes.android_kotlin_mvvm_architecture.databinding.ActivitySplashBinding
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.base.BaseActivity
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.login.LoginActivity
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.main.MainActivity

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), Splash_.Navigator {

    // VARIABLES
    // ====================================================
    private lateinit var mSplashViewModel: SplashViewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() {
            mSplashViewModel = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)
            return mSplashViewModel
        }

    // FUNCTIONS
    // ====================================================
    // => LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSplashViewModel.navigator = this
        mSplashViewModel.startSeeding()
    }

    // => Primary
    override fun openLoginActivity() {
        val intent = LoginActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    override fun openMainActivity() {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

}