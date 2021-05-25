package io.github.aiwithab.crudroom.db


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import io.github.aiwithab.crudroom.models.UserModel

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class UserDataBase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {
        private var INSTANCE: UserDataBase? = null

        fun getInstance(context: Context): UserDataBase? {
            if (INSTANCE == null) {
                synchronized(UserDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        UserDataBase::class.java, "user.db").allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}