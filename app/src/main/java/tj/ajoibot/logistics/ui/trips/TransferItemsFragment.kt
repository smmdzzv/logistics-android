package tj.ajoibot.logistics.ui.trips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.generic.instance
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModel
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModelFactory

class TransferItemsFragment : BaseTripFragment() {
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
        return inflater.inflate(R.layout.fragment_transfer_items, container, false)
    }

}