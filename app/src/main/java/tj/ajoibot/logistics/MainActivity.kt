package tj.ajoibot.logistics

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import tj.ajoibot.donishgoh.internal.permissions.PermissionsRequestFactory
import tj.ajoibot.logistics.internal.helpers.permissions.PermissionAskListener
import tj.ajoibot.logistics.ui.main.MainViewModel
import tj.ajoibot.logistics.ui.main.MainViewModelFactory

class MainActivity : AppCompatActivity(), KodeinAware, PermissionAskListener.PermissionFactoryListener {

    override val kodein: Kodein by closestKodein()

    private val mainViewModelFactory: MainViewModelFactory by instance()

    private lateinit var permissionsRequestFactory: PermissionsRequestFactory

    lateinit var vm: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_trips,
//                R.id.navigation_profile,
//                R.id.navigation_itemInfo
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        vm = ViewModelProvider(this, mainViewModelFactory)
            .get(MainViewModel::class.java)

        permissionsRequestFactory = PermissionsRequestFactory(this)
        permissionsRequestFactory.checkCameraPermission()

    }


    fun onUserLogout() {
        launchLoginActivity()
        finish()
    }

    private fun launchLoginActivity() {
        val activityIntent = Intent(this, LoginActivity::class.java)
        startActivity(activityIntent)
    }

    override fun onPermissionGrantedReceive(requestCode: Int) {
        if (requestCode == R.integer.camera_permission_code) {
            Log.d("permissions", "Camera permission gained")
        }
    }
}
