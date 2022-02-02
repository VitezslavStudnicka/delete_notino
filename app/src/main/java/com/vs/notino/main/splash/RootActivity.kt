package com.vs.notino.main.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.vs.notino.MainActivity
import com.vs.notino.R
import com.vs.notino.extensions.TAG
import com.vs.notino.main.login.LoginActivity
import com.vs.notino.utils.DataStoreManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {

    @Inject
    lateinit var dsManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        // DataStore example
        lifecycleScope.launch {
            dsManager.saveToken("sxcyc")
            dsManager.getToken().collect { token ->
                Log.d(TAG, "saved token $token")
                intent = if (!token.isNullOrEmpty()) {
                    Intent(this@RootActivity, MainActivity::class.java)
                } else {
                    Intent(this@RootActivity, LoginActivity::class.java)
                }
                startActivity(intent)
                finish()
            }
        }
    }

}