package tj.ajoibot.logistics.ui.handlers

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.internal.interfaces.IElementClickHandler
import tj.ajoibot.logistics.ui.trips.TripsFragmentDirections

class ActiveTripClickHandler (
    private val fragment: Fragment,
    private val trip: ActiveTrip
): IElementClickHandler{

    override fun onElementClick() {
//        val bundle = Bundle()
//        bundle.putString("tripId", trip.id)
//
//        val detailsFragment = TripDetailsFragment.newInstance()
//        detailsFragment.arguments = bundle
//
//        (fragment.activity as MainActivity).replaceFragmentWithBackStack(detailsFragment, R.id.nav_host_fragment, TRIP_DETAILS_TAG,
//            TRIPS_BACK_STACK )
        val action = TripsFragmentDirections.showTripDetails(trip.id)
        fragment.findNavController().navigate(action)
    }
}