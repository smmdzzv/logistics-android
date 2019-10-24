package tj.ajoibot.ajoibotlogistics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.ui.login.LoginViewModel
import tj.ajoibot.ajoibotlogistics.ui.login.LoginViewModelFactory
import tj.ajoibot.ajoibotlogistics.data.models.Result.Status
import tj.ajoibot.ajoibotlogistics.internal.utils.SharedSettings

class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val loginViewModelFactory: LoginViewModelFactory by instance()

    lateinit var viewModel: LoginViewModel

    private val settings: SharedSettings by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, loginViewModelFactory)
            .get(LoginViewModel::class.java)

        setListeners()
    }

    private fun setListeners() {
        login_btn.setOnClickListener { authorize() }

        viewModel.authorizeResponse.observe(this, Observer { response ->
            if (response.status === Status.SUCCESS) {
                settings.saveToken(response.data!!.token)
                launchMainActivity()
                finish()
            } else
                activeState()
        })
    }

    private fun launchMainActivity() {
        val activityIntent = Intent(this, MainActivity::class.java)
        startActivity(activityIntent)
    }

    private fun authorize() {
        val credentials = Credentials(email_et.text.toString(), password_et.text.toString())
        if (credentials.validate()) {
            busyState()
            viewModel.authorize(credentials)
        }
    }

    private fun busyState() {
        progress_circular.visibility = View.VISIBLE
        login_btn.visibility = View.GONE
    }

    private fun activeState() {
        progress_circular.visibility = View.GONE
        login_btn.visibility = View.VISIBLE
    }
}
