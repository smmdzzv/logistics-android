package tj.ajoibot.logistics.ui.trips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_load_car.*
import kotlinx.android.synthetic.main.fragment_navigation_item_info.*
import kotlinx.android.synthetic.main.fragment_unload_car.*
import org.kodein.di.generic.instance
import tj.ajoibot.logistics.MainActivity
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.internal.extensions.addFragment
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModel
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModelFactory
import tj.ajoibot.logistics.ui.barcode.BarcodeScannerFragment

class LoadCarFragment : BaseTripFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_load_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = BarcodeScannerFragment()
        (activity as MainActivity).addFragment(fragment, load_frame.id)

        setObservers()
    }

    private fun setObservers() {
        barcodeVm.decodedBarCode.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { decoded ->
                load_status_tv.text = decoded
                val itemId = vm.selectedTrip?.id
                if (itemId !== null)
                    tripsVm.loadItem(itemId, decoded)
            }
        })

        tripsVm.sendingRequest.observe(viewLifecycleOwner, Observer { busy ->
            barcodeVm.setScanningMode(!busy)
            load_progress_bar.visibility = if (busy) View.VISIBLE else View.GONE
        })

        tripsVm.statusMessage.observe(viewLifecycleOwner, Observer { status ->
            val message = status.getContentIfNotHandled()
            if(message != null)
                load_status_tv.text = message
        })
    }

}
