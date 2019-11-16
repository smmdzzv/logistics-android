package tj.ajoibot.logistics.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_navigation_item_info.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.logistics.MainActivity
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.internal.extensions.addFragment
import tj.ajoibot.logistics.ui.barcode.BarcodeScannerFragment

class NavigationItemInfoFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()

    private lateinit var storedItemsViewModel: StoredItemViewModel

    private val storedItemViewModelFactory: StoredItemViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_navigation_item_info, container, false)
        //TODO refactor this
        storedItemsViewModel = activity?.run {
            ViewModelProvider(this, storedItemViewModelFactory)
                .get(StoredItemViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = BarcodeScannerFragment()
        (activity as MainActivity).addFragment(fragment, item_info_frame.id)
    }
}
