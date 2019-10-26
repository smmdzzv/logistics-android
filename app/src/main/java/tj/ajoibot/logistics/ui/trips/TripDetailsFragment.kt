package tj.ajoibot.logistics.ui.trips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import tj.ajoibot.logistics.R

class TripDetailsFragment : Fragment() {

    val args: TripDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_trip_details, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)

//        val tripId = arguments?.getString("tripId")

        textView.text = args.tripId

        return root
    }

    companion object {
        fun newInstance() = TripDetailsFragment()
    }
}