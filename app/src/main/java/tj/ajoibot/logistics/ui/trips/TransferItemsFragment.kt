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

        val activeTrips = vm.activeTrips.value?.data?.map { it.code }

        if (activeTrips != null) {
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
        barcodeVm.decodedBarCode.observe(viewLifecycleOwner, Observer { decoded ->
            val itemId = vm.selectedTrip?.id
            if (itemId !== null && decoded !== null)
                tripsVm.unloadItem(itemId, decoded)
        })

        barcodeVm.sendingRequest.observe(viewLifecycleOwner, Observer { busy ->
            transfer_progress_bar.visibility = if (busy) View.VISIBLE else View.GONE
        })

        barcodeVm.statusMessage.observe(viewLifecycleOwner, Observer { status ->
            transfer_status_tv.text = status
        })
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val trips = vm.activeTrips.value?.data
        val selected = parent?.getItemAtPosition(position) as String
        if(trips != null && selected.isNotBlank()){
            val trip = trips.firstOrNull{
                it.code == selected
            }
            tripsVm.setTargetTrip(trip)
        }

    }
}