package tj.ajoibot.donishgoh.internal.permissions

import android.Manifest
import android.app.Activity
import tj.ajoibot.logistics.R
import tj.ajoibot.logistics.internal.helpers.permissions.PermissionAskListener
import tj.ajoibot.logistics.internal.helpers.permissions.PermissionHelper

class PermissionsRequestFactory(private val activity: Activity) {
    fun checkStoragePermission(){
        val permissionAskListener = PermissionAskListener(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
            "Для обмена файлами с сервером необходимо предоставить доступ к файловой системе",
            activity.resources.getInteger(R.integer.storage_permission_code),
            activity
        )

        PermissionHelper.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, activity, permissionAskListener)
    }

    fun checkPhoneStatePermission(){
        val phoneStateAskListener = PermissionAskListener(arrayOf(Manifest.permission.READ_PHONE_STATE),
            "Данное разрешение необходимо в рамках условий пользования системой",
            activity.resources.getInteger(R.integer.read_phone_state_permission_code),
            activity
        )

        PermissionHelper.checkPermission(Manifest.permission.READ_PHONE_STATE, activity, phoneStateAskListener)
    }

    fun checkCameraPermission(){
        val phoneStateAskListener = PermissionAskListener(arrayOf(Manifest.permission.CAMERA),
            "Для считывания штрих кодов необходимо предоставить доступ к камере",
            activity.resources.getInteger(R.integer.camera_permission_code),
            activity
        )

        PermissionHelper.checkPermission(Manifest.permission.CAMERA, activity, phoneStateAskListener)
    }
}