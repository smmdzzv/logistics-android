package tj.ajoibot.logistics.ui.item

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_navigation_item_info.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.logistics.MainActivity
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.internal.BASE_URL
import tj.ajoibot.logistics.internal.extensions.addFragment
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModel
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModelFactory
import tj.ajoibot.logistics.ui.barcode.BarcodeScannerFragment

class NavigationItemInfoFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()

    private lateinit var storedItemsVm: StoredItemViewModel
    private lateinit var barcodeVm: BarCodeViewModel

    private val storedItemViewModelFactory: StoredItemViewModelFactory by instance()
    private val barCodeViewModelFactory: BarCodeViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_navigation_item_info, container, false)
        //TODO refactor this
        storedItemsVm = activity?.run {
            ViewModelProvider(this, storedItemViewModelFactory)
                .get(StoredItemViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        barcodeVm = activity?.run {
            ViewModelProvider(this, barCodeViewModelFactory)
                .get(BarCodeViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = BarcodeScannerFragment()
        (activity as MainActivity).addFragment(fragment, item_info_frame.id)

        setObservers()
    }

    @SuppressLint("SetTextI18n")
    private fun setObservers() {
        barcodeVm.decodedBarCode.observe(viewLifecycleOwner, Observer { decoded ->
            storedItemsVm.getStoredItem(decoded)
            val data = storedItemsVm.storedItemResponse.value?.data
            if (data !== null){
                val volume = data.info.width * data.info.height * data.info.length
                item_info_status_tv.text = "Код: ${data.code}\n" +
                        "Объем: $volume | Вес: ${data.info.weight} " +
                        "| ШхВхД: ${data.info.width}x${data.info.height}x${data.info.length}\n" +
                        "Владелец:${data.info.owner.code} ${data.info.owner.name}\n" +
                        "${BASE_URL}stored/${data.id}"
            }
        })

        storedItemsVm.sendingRequest.observe(viewLifecycleOwner, Observer { busy ->
            item_info_progress_bar.visibility = if (busy) View.VISIBLE else View.GONE
        })

    }
}
