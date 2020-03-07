package n7.myperfectemptyproject.data.source.local.converter

import android.net.Uri
import androidx.core.net.toUri
import androidx.room.TypeConverter

/**
 * Converts a [Uri] into and from a persisted value
 */
class UriTypeConverter {
    @TypeConverter
    fun fromString(uriString: String?): Uri? {
        return uriString?.toUri()
    }

    @TypeConverter
    fun uriToString(uri: Uri?): String? {
        return uri?.toString()
    }
}
