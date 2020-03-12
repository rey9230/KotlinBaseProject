package n7.myperfectemptyproject.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import n7.myperfectemptyproject.data.source.local.converter.DateConverter
import n7.myperfectemptyproject.data.source.local.model.LocalUser

private const val DB_VERSION = 1

@Database(entities = [LocalUser::class], version = DB_VERSION, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val myDao: UsersDao

    companion object {
        const val DB_NAME = "AppDatabase$DB_VERSION.db"
    }
}
