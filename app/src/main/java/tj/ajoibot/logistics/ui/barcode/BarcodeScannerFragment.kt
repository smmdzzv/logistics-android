package tj.ajoibot.logistics.ui.barcode

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.Result
import kotlinx.android.synthetic.main.fragment_barcode_scanner.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import tj.ajoibot.logistics.R

class BarcodeScannerFragment : Fragment(), ZXingScannerView.ResultHandler {

    private val FLASH_STATE = "FLASH_STATE"

    private lateinit var mScannerView: ZXingScannerView
    private var mFlash: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_barcode_scanner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mScannerView = ZXingScannerView(activity)
        scanner_content_frame.addView(mScannerView)
        scanner_flash_btn.setOnClickListener { toggleFlash() }
    }

    override fun onResume() {
        super.onResume()
        mScannerView.setAspectTolerance(0.2f)
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
        mScannerView.flash = mFlash
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FLASH_STATE, mFlash)
    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(
            activity, "Contents = " + rawResult?.text +
                    ", Format = " + rawResult?.barcodeFormat.toString(), Toast.LENGTH_SHORT
        ).show()

        val handler = Handler()
        handler.postDelayed({ mScannerView.resumeCameraPreview(this) }, 2000)
    }

    private fun toggleFlash() {
        mFlash = !mFlash
        mScannerView.flash = mFlash
    }

    companion object {
        fun newInstance() = BarcodeScannerFragment()
    }
}