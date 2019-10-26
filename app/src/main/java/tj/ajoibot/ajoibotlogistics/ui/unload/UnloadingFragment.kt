package tj.ajoibot.ajoibotlogistics.ui.unload

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class UnloadingFragment : Fragment(), ZXingScannerView.ResultHandler {

    private val FLASH_STATE = "FLASH_STATE"

    private lateinit var mScannerView: ZXingScannerView
    private var mFlash: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mScannerView = ZXingScannerView(activity)
        return mScannerView
    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(
            activity, "Contents = " + rawResult?.text +
                    ", Format = " + rawResult?.barcodeFormat.toString(), Toast.LENGTH_SHORT
        ).show()

        val handler = Handler()
        handler.postDelayed({ mScannerView.resumeCameraPreview(this) }, 2000)
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }
}