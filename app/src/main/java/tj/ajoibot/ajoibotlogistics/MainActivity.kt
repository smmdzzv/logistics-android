package tj.ajoibot.ajoibotlogistics

import android.content.Intent
import android.os.Bundle
import android.view.Window
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.ajoibotlogistics.ui.main.MainViewModel
import tj.ajoibot.ajoibotlogistics.ui.main.MainViewModelFactory

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val mainViewModelFactory: MainViewModelFactory by instance()

    private lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_loading,
                R.id.navigation_unloading,
                R.id.navigation_transfer,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        vm =
            ViewModelProvider(this.application as LogisticsApplication, mainViewModelFactory)
                .get(MainViewModel::class.java)

    }

    fun onUserLogout(){
        launchLoginActivity()
        finish()
    }

    private fun launchLoginActivity() {
        val activityIntent = Intent(this, LoginActivity::class.java)
        startActivity(activityIntent)
    }
}
