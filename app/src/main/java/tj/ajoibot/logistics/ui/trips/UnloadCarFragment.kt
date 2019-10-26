package tj.ajoibot.logistics.ui.trips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_unload_car.*
import org.kodein.di.generic.instance
import tj.ajoibot.logistics.MainActivity
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.internal.extensions.addFragment
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModel
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModelFactory
import tj.ajoibot.logistics.ui.barcode.BarcodeScannerFragment
import tj.ajoibot.logistics.ui.main.MainViewModelFactory

class UnloadCarFragment : BaseTripFragment() {

    private lateinit var barcodeVm: BarCodeViewModel

    private val barCodeViewModelFactory: BarCodeViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        barcodeVm = activity?.run {
            ViewModelProvider(this, barCodeViewModelFactory)
                .get(BarCodeViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_unload_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = BarcodeScannerFragment()
        (activity as MainActivity).addFragment(fragment, unload_car_frame.id)

        setObservers() 
    }

    private fun setObservers() {
        barcodeVm.decodedBarCode.observe(viewLifecycleOwner, Observer { decoded ->
            val itemId = vm.selectedTrip?.id
            if (itemId !== null && decoded !== null)
                barcodeVm.unloadItem(itemId, decoded)
        })
    }
}
