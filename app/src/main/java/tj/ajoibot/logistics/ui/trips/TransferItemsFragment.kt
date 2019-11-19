package tj.ajoibot.logistics.ui.trips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_transfer_items.*
import tj.ajoibot.logistics.MainActivity
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.internal.extensions.addFragment
import tj.ajoibot.logistics.ui.barcode.BarcodeScannerFragment
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.fragment_load_car.*


class TransferItemsFragment : BaseTripFragment(), AdapterView.OnItemSelectedListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transfer_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = BarcodeScannerFragment()
        (activity as MainActivity).addFragment(fragment, transfer_frame.id)

        var activeTrips = vm.activeTrips.value?.data?.map { it.code }

        if (activeTrips != null) {
            activeTrips = activeTrips.filter { it != vm.selectedTrip?.code }
            val adapter = ArrayAdapter(
                this.context!!,
                android.R.layout.simple_spinner_item,
                activeTrips
            )

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            transfer_trips_spinner.adapter = adapter

            transfer_trips_spinner.onItemSelectedListener = this
        }


        setObservers()
    }

    private fun setObservers() {
        barcodeVm.decodedBarCode.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { decoded ->
                val itemId = vm.selectedTrip?.id
                val targetItemId = tripsVm.targetTrip.value?.id
                if (itemId !== null && targetItemId !== null)
                    tripsVm.transferItem(itemId, targetItemId, decoded)
            }
        })

        tripsVm.sendingRequest.observe(viewLifecycleOwner, Observer { busy ->
            transfer_progress_bar.visibility = if (busy) View.VISIBLE else View.GONE
        })

        tripsVm.statusMessage.observe(viewLifecycleOwner, Observer { status ->
            val message = status.getContentIfNotHandled()
            if (message != null)
                transfer_status_tv.text = message
        })
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val trips = vm.activeTrips.value?.data
        val selected = parent?.getItemAtPosition(position) as String
        if (trips != null && selected.isNotBlank()) {
            val trip = trips.firstOrNull {
                it.code == selected
            }
            tripsVm.setTargetTrip(trip)
        }

    }
}