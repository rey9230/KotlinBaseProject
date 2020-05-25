package n7.myperfectemptyproject.utils.extension

import android.content.Context
import android.os.Environment
import java.io.File

fun Context.getMyCacheDir(): File {
    val externalExist = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    if (externalExist) {
        return applicationContext.externalCacheDir!!
    }
    return applicationContext.cacheDir
}