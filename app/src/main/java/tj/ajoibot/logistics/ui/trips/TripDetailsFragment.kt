package tj.ajoibot.logistics.ui.trips

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_trip_details.*
import tj.ajoibot.logistics.R

class TripDetailsFragment : BaseTripFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_trip_details, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trip_details_code_tv.text = "Рейс: ${vm.selectedTrip?.code}"
        setListeners()
    }

    private fun setListeners() {

        trip_details_load_btn.setOnClickListener {
            val action = TripDetailsFragmentDirections.navigateToLoadCarFragment()
            findNavController().navigate(action)
        }

        trip_details_unload_btn.setOnClickListener {
            val action = TripDetailsFragmentDirections.navigateToUnloadCarFragment()
            findNavController().navigate(action)
        }

        trip_details_transfer_btn.setOnClickListener {
            val action = TripDetailsFragmentDirections.navigateToTransferItemsFragment()
            findNavController().navigate(action)
        }
    }

    companion object {
        fun newInstance() = TripDetailsFragment()
    }
}