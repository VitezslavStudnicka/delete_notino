package com.vs.notino.main.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.vs.notino.MainActivity
import com.vs.notino.R
import com.vs.notino.extensions.TAG
import com.vs.notino.main.login.LoginActivity
import com.vs.notino.utils.DataStoreManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // DataStore example
        val dsManager = DataStoreManager(this)
        lifecycleScope.launch {
            dsManager.saveToken("sxcyc")
            dsManager.getToken().collect { token ->
                Log.d(TAG, "saved token $token")
                intent = if (!token.isNullOrEmpty()) {
                    Intent(this@SplashActivity, MainActivity::class.java)
                } else {
                    Intent(this@SplashActivity, LoginActivity::class.java)
                }
                startActivity(intent)
                finish()
            }
        }
    }

}