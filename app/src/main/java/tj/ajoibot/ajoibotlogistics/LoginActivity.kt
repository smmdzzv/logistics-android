package tj.ajoibot.ajoibotlogistics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.ajoibotlogistics.data.models.request.Credentials
import tj.ajoibot.ajoibotlogistics.ui.login.LoginViewModel
import tj.ajoibot.ajoibotlogistics.ui.login.LoginViewModelFactory

class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()
    private val loginViewModelFactory: LoginViewModelFactory by instance()
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this, loginViewModelFactory)
            .get(LoginViewModel::class.java)

        login_btn.setOnClickListener { authorize() }
    }

    private fun authorize(){
        val credentials = Credentials(email_et.text.toString(), password_et.text.toString())
        viewModel.authorize(credentials)
    }
}
