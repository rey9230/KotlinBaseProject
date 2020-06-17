package n7.myperfectemptyproject.utils.extension

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.core.app.ShareCompat

fun Activity.share(uri: Uri) {
    val intent = ShareCompat.IntentBuilder.from(this)
        .setType("audio/mp4")
        .setStream(uri)
        .createChooserIntent()
        .addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        .addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
    startActivity(intent)
}
