package n7.myperfectemptyproject.utils.extension

import android.app.Activity
import android.net.Uri
import androidx.core.app.ShareCompat

fun Activity.share(uri: Uri) {
    val intent = ShareCompat.IntentBuilder.from(this)
        .setType("audio/mp4")
        .setStream(uri)
        .createChooserIntent()
    startActivity(intent)
}