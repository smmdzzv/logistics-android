package tj.ajoibot.logistics.ui.trips

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_trips.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.ui.adapters.TripsRecyclerViewAdapter
import tj.ajoibot.logistics.ui.main.MainViewModel
import tj.ajoibot.logistics.ui.main.MainViewModelFactory

class TripsFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private lateinit var vm: MainViewModel

    private val mainViewModelFactory: MainViewModelFactory by instance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vm = activity?.run {
            ViewModelProvider(this, mainViewModelFactory)
                .get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        return inflater.inflate(R.layout.fragment_trips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TripsRecyclerViewAdapter(this, vm)
        trips_rv.layoutManager = LinearLayoutManager(this.context)
        trips_rv.adapter = adapter
        trips_rv.itemAnimator = DefaultItemAnimator()
        val divider = DividerItemDecoration(trips_rv.context,
            LinearLayoutManager(this.context).orientation
        )
        trips_rv.addItemDecoration(divider)

        vm.activeTrips.observe(viewLifecycleOwner, Observer { trips ->
            Log.d("trips", "Have trips in fragment $$trips")
            if (trips.data != null)
                adapter.setData(trips.data)
        })

        vm.getActiveTrips()

    }
}