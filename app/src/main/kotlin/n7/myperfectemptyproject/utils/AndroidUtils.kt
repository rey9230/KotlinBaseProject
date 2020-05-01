package n7.myperfectemptyproject.utils

import android.app.Application
import android.os.Environment
import java.io.File

fun getCacheDir(applicationContext: Application): File {
    var state: String? = null
    try {
        state = Environment.getExternalStorageState()
    } catch (e: Exception) {

    }
    if (state == null || state.startsWith(Environment.MEDIA_MOUNTED)) {
        try {
            val file: File? = applicationContext.externalCacheDir
            if (file != null) {
                return file
            }
        } catch (e: Exception) {

        }
    }
    try {
        val file: File? = applicationContext.cacheDir
        if (file != null) {
            return file
        }
    } catch (e: Exception) {

    }
    return File("")
}