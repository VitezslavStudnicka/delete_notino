package com.vs.notino.main.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vs.notino.MainActivity
import com.vs.notino.R
import com.vs.notino.main.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // DataStore example, is authentica
        val authenticated = true
        var intent: Intent
        if (true) { // isauthenticated
            intent = Intent(this, MainActivity::class.java)
        } else {
            intent = Intent(this, LoginActivity::class.java)
        }
        startActivity(intent)
    }
}