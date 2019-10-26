package tj.ajoibot.logistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.internal.utils.SharedSettings
import tj.ajoibot.logistics.ui.main.MainViewModel
import tj.ajoibot.logistics.ui.main.MainViewModelFactory

class MainEmptyActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val mainViewModelFactory: MainViewModelFactory by instance()

    private lateinit var vm: MainViewModel

    private val settings: SharedSettings by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_empty)

        vm = ViewModelProvider(this.application as LogisticsApplication, mainViewModelFactory)
            .get(MainViewModel::class.java)

        if (settings.getToken().isNullOrBlank())
            toLoginActivity()
        else
            vm.getAuthorizedUser()

        setObservers()
    }

    private fun setObservers() {
        vm.userResponse.observe(this, Observer { response ->
            if (response.status === Result.Status.SUCCESS)
                toMainActivity()
            else
                toLoginActivity()
        })
    }

    private fun toLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        navigate(intent)
    }

    private fun toMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        navigate(intent)
    }

    private fun navigate(activityIntent: Intent) {
        startActivity(activityIntent)
        finish()
    }
}
