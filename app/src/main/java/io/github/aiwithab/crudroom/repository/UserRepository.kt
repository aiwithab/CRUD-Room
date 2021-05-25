package io.github.aiwithab.crudroom.repository

import android.content.Context
import android.os.AsyncTask
import io.github.aiwithab.crudroom.db.UserDao
import io.github.aiwithab.crudroom.db.UserDataBase
import io.github.aiwithab.crudroom.models.UserModel

class UserRepository(context: Context) {

    var db: UserDao = UserDataBase.getInstance(context)?.userDao()!!


    //Fetch All the Users
    fun getAllUsers(): List<UserModel> {
        return db.gelAllUsers()
    }

    // Insert new user
    fun insertUser(users: UserModel) {
        InsertAsyncTask(db).execute(users)
    }

    // update user
    fun updateUser(users: UserModel) {
        db.updateUser(users)
    }

    // Delete user
    fun deleteUser(users: UserModel) {
        db.deleteUser(users)
    }

    private class InsertAsyncTask internal constructor(private val usersDao: UserDao) :
        AsyncTask<UserModel, Void, Void>() {

        override fun doInBackground(vararg params: UserModel): Void? {
            usersDao.insertUser(params[0])
            return null
        }
    }
}