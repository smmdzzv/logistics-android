package tj.ajoibot.ajoibotlogistics

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        wakeUpSession()
    }

    private fun wakeUpSession() {
        val token = application
            .getSharedPreferences("tj.ajoibot.logistics.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE)
            .getString("token", null)
        if (token != null) {
             Log.d("sdf", "sdf")
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, resources.getInteger(R.integer.login_code))
        }
    }
}
