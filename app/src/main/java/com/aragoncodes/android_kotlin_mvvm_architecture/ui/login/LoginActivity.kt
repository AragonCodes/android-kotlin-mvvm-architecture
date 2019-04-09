package com.aragoncodes.android_kotlin_mvvm_architecture.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.aragoncodes.android_kotlin_mvvm_architecture.BR
import com.aragoncodes.android_kotlin_mvvm_architecture.R
import com.aragoncodes.android_kotlin_mvvm_architecture.databinding.ActivityLoginBinding
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.base.BaseActivity
import com.aragoncodes.android_kotlin_mvvm_architecture.ui.main.MainActivity
import com.aragoncodes.android_kotlin_mvvm_architecture.util.CommonUtils
import kotlinx.android.synthetic.main.activity_login.*
import timber.log.Timber

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), Login_.Navigator {

    // VARIABLES
    // ====================================================
    private lateinit var mLoginViewModel: LoginViewModel
    private lateinit var vBinding: ActivityLoginBinding
    override val layoutId: Int
        get() = R.layout.activity_login
    override val viewModel: LoginViewModel
        get() {
            mLoginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
            return mLoginViewModel
        }

    // FUNCTIONS
    // ====================================================
    // => Lifecycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vBinding = viewDataBinding!!
        mLoginViewModel.navigator = this

        initView()

    }
    private fun initView() {
        initClick()
    }
    private fun initClick() {
        vBinding.bttLogin.setOnClickListener {
            login()
        }
    }

    // => Primary
    override fun handleError(throwable: Throwable) {
        Timber.d("handleError", throwable)
    }

    override fun login() {
        val email = vBinding!!.etEmail.getText().toString()
        val password = vBinding!!.etPassword.getText().toString()
        if (CommonUtils.isEmailAndPasswordValid(email, password)) {
            hideKeyboard()
            mLoginViewModel.login(email, password)
        } else {
            showMessage(getString(R.string.invalid_email_password))
        }
    }

    override fun openMainActivity() {
        val intent = MainActivity.newIntent(this)
        startActivity(intent)
        finish()
    }

    // Public Static
    // ====================================================
    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }
}