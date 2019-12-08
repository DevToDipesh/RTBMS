package `in`.terxlabs.rtbms.base

import `in`.terxlabs.rtbms.R
import `in`.terxlabs.rtbms.welcome.WelcomeActivity
import android.content.Intent
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        this.requestedOrientation = SCREEN_ORIENTATION_PORTRAIT
        val hander = Handler()

            hander.postDelayed({ startActivity(Intent(this@SplashActivity, WelcomeActivity::class.java)) }, 1000)


    }
}
