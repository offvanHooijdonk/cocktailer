package by.off.cocktailer.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.off.cocktailer.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToMain()
    }

    private fun navigateToMain() {
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        startActivity(Intent(this, MainActivity::class.java))
    }
}