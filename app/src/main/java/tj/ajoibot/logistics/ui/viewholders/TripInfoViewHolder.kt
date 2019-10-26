package tj.ajoibot.logistics.ui.viewholders

import androidx.recyclerview.widget.RecyclerView
import tj.ajoibot.logistics.data.models.response.ActiveTrip
import tj.ajoibot.logistics.databinding.TripInfoDataBinding

class TripInfoViewHolder(private val dataBinding: TripInfoDataBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(item: ActiveTrip) {
        dataBinding.item = item
        dataBinding.executePendingBindings()
    }

    fun getDataBinding(): TripInfoDataBinding {
        return dataBinding
    }
}