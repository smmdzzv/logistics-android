package tj.ajoibot.logistics.internal.helpers.permissions

import android.app.Activity
import android.app.AlertDialog
import android.util.Log
import androidx.core.app.ActivityCompat


//TODO remove context
class PermissionAskListener(
    val permissions: Array<String>,
    val message: String,
    val requestCode: Int,
    val activity: Activity
): PermissionHelper.PermissionAskListener{

    override fun onNeedPermission() {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    override fun onPermissionPreviouslyDenied() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Запрос на получение разрешение")
        builder.setCancelable(true)
        builder.setMessage(message)
        builder.setPositiveButton("Да"){dialog, which ->
            this.onNeedPermission()
        }
        builder.setNegativeButton("Отмена")
        { dialog, _ ->
            //cancel the dialog if Cancel clicks
            dialog.cancel()
        }
        val alert = builder.create()
        alert.show()
    }

    override fun onPermissionDisabled() {
       Log.d("permissions", "On permission disabled")
    }

    override fun onPermissionGranted() {
        listener?.onPermissionGrantedReceive(requestCode)
    }

    interface PermissionFactoryListener {
        fun onPermissionGrantedReceive(requestCode: Int)
    }

    companion object {
        var listener: PermissionFactoryListener? = null
    }

}