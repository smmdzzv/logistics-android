package tj.ajoibot.logistics.ui.handlers

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.internal.interfaces.IElementClickHandler
import tj.ajoibot.logistics.ui.main.MainViewModel
import tj.ajoibot.logistics.ui.trips.TripsFragmentDirections

class ActiveTripClickHandler (
    private val fragment: Fragment,
    private val trip: ActiveTrip,
    private val vm: MainViewModel
): IElementClickHandler{

    override fun onElementClick() {
        vm.selectedTrip = trip
        val action = TripsFragmentDirections.showTripDetails()
        fragment.findNavController().navigate(action)
    }
}