package tj.ajoibot.ajoibotlogistics.ui.unload

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import tj.ajoibot.ajoibotlogistics.R

class UnloadingFragment : Fragment() {

    private lateinit var unloadingViewModel: UnloadingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        unloadingViewModel =
            ViewModelProviders.of(this).get(UnloadingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_unloading, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        unloadingViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}