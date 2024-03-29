package tj.ajoibot.logistics.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_profile.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.logistics.LogisticsApplication
import tj.ajoibot.logistics.MainActivity
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.data.models.Result
import tj.ajoibot.logistics.data.models.response.AuthorizedUserResponse
import tj.ajoibot.logistics.internal.utils.SharedSettings
import tj.ajoibot.logistics.ui.main.MainViewModel
import tj.ajoibot.logistics.ui.main.MainViewModelFactory

class ProfileFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val settings: SharedSettings by instance()

    private val mainViewModelFactory: MainViewModelFactory by instance()

    private lateinit var vm: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        //TODO refactor this
        vm =
            ViewModelProvider(
                this.activity?.application as LogisticsApplication,
                mainViewModelFactory
            )
                .get(MainViewModel::class.java)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setObservers()

        setListeners()

        vm.getAuthorizedUser()
    }

    private fun setListeners() {
        profile_logout_btn.setOnClickListener { logout() }
    }

    private fun logout() {
        settings.saveToken("")
        (this.activity as MainActivity).onUserLogout()
    }

    //TODO error handling
    private fun setObservers() {
        vm.userResponse.observe(this, Observer { response ->
            if (response.status == Result.Status.SUCCESS)
                updateUi(response.data!!)
        })
    }

    private fun updateUi(info: AuthorizedUserResponse) {
        profile_name_tv.text = info.name
        profile_email_tv.text = info.email
        profile_code_tv.text = info.code
        profile_branch_tv.text = info.branch.name
    }

}
