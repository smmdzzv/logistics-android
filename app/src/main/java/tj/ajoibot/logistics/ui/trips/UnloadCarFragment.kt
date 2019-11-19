package tj.ajoibot.logistics.ui.trips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_unload_car.*
import tj.ajoibot.logistics.MainActivity
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.internal.extensions.addFragment
import tj.ajoibot.logistics.ui.barcode.BarcodeScannerFragment

class UnloadCarFragment : BaseTripFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_unload_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = BarcodeScannerFragment()
        (activity as MainActivity).addFragment(fragment, unload_car_frame.id)

        setObservers()
    }

    private fun setObservers() {
        barcodeVm.decodedBarCode.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { decoded ->
                val itemId = vm.selectedTrip?.id
                if (itemId !== null)
                    tripsVm.unloadItem(itemId, decoded)
            }
        })

        tripsVm.sendingRequest.observe(viewLifecycleOwner, Observer { busy ->
            unload_car_progress_bar.visibility = if (busy) View.VISIBLE else View.GONE
        })

        tripsVm.statusMessage.observe(viewLifecycleOwner, Observer { status ->
            val message = status.getContentIfNotHandled()
            if(message != null)
                unload_car_status_tv.text = message
        })
    }
}
