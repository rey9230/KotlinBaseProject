package n7.myperfectemptyproject.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import n7.myperfectemptyproject.data.source.local.model.User

private const val DB_VERSION = 1

@Database(entities = [User::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val myDao: UsersDao

    companion object {
        const val DB_NAME = "AppDatabase$DB_VERSION.db"
    }
}
