package tj.ajoibot.logistics.ui.trips

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModel
import tj.ajoibot.logistics.ui.barcode.BarCodeViewModelFactory
import tj.ajoibot.logistics.ui.main.MainViewModel
import tj.ajoibot.logistics.ui.main.MainViewModelFactory

open class BaseTripFragment : Fragment(), KodeinAware {
    override val kodein by closestKodein()

    protected lateinit var vm: MainViewModel
    protected lateinit var barcodeVm: BarCodeViewModel
    protected lateinit var tripsVm: TripsViewModel

    private val mainViewModelFactory: MainViewModelFactory by instance()
    private val barCodeViewModelFactory: BarCodeViewModelFactory by instance()
    private val tripsViewModelFactory: TripsViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = activity?.run {
            ViewModelProvider(this, mainViewModelFactory)
                .get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        barcodeVm = activity?.run {
            ViewModelProvider(this, barCodeViewModelFactory)
                .get(BarCodeViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        tripsVm = activity?.run {
            ViewModelProvider(this, tripsViewModelFactory)
                .get(TripsViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }
}