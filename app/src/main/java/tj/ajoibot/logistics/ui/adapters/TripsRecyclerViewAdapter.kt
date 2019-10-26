package tj.ajoibot.logistics.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.databinding.TripInfoDataBinding
import tj.ajoibot.logistics.ui.handlers.ActiveTripClickHandler
import tj.ajoibot.logistics.ui.main.MainViewModel
import tj.ajoibot.logistics.ui.viewholders.TripInfoViewHolder

class TripsRecyclerViewAdapter(
    private val fragment: Fragment,
    private val vm: MainViewModel
): RecyclerView.Adapter<TripInfoViewHolder>() {

    private var activeTrips: List<ActiveTrip> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TripInfoViewHolder {
        val dataBinding = TripInfoDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TripInfoViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return activeTrips.size
    }

    override fun onBindViewHolder(holder: TripInfoViewHolder, position: Int) {
        val item = activeTrips[position]
        holder.bind(item)
        val dataBinding = holder.getDataBinding()
        dataBinding.handler = ActiveTripClickHandler(fragment, item, vm)
    }

    public fun setData(newData: List<ActiveTrip>){
        activeTrips = newData
        notifyDataSetChanged()
    }
}