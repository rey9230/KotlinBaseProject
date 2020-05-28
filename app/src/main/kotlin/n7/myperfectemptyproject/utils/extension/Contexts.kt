package n7.myperfectemptyproject.utils.extension

import android.content.Context
import android.graphics.Bitmap
import android.os.Environment
import androidx.core.content.FileProvider
import n7.myperfectemptyproject.BuildConfig
import java.io.File
import java.io.FileOutputStream

fun Context.getUri(file: File) {
    FileProvider.getUriForFile(this, "${BuildConfig.APPLICATION_ID}.provider", file)
}

fun Context.getMyCacheDir(): File {
    val externalExist = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    if (externalExist) {
        return applicationContext.externalCacheDir!!
    }
    return applicationContext.cacheDir
}

fun Context.saveBitmapInCacheDir(bitmap: Bitmap) {
    val file = File(getMyCacheDir(), "cache.png")
    file.mkdirs()
    file.delete()
    file.createNewFile()

    val fileOutputStream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
    fileOutputStream.flush()
    fileOutputStream.close()

    return getUri(file)
}