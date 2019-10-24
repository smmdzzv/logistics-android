package tj.ajoibot.ajoibotlogistics.ui.load

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import tj.ajoibot.ajoibotlogistics.R

class LoadingFragment : Fragment() {

    private lateinit var loadingViewModel: LoadingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadingViewModel =
            ViewModelProviders.of(this).get(LoadingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_loading, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        loadingViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}