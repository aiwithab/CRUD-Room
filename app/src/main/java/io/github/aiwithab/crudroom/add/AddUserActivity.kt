package io.github.aiwithab.crudroom.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import io.github.aiwithab.crudroom.R
import io.github.aiwithab.crudroom.models.UserModel
import io.github.aiwithab.crudroom.repository.UserRepository

class AddUserActivity : AppCompatActivity() {

    var user: UserModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        val userName: EditText = findViewById(R.id.ed_username)
        val fullName: EditText = findViewById(R.id.ed_fullName)
        val address: EditText = findViewById(R.id.ed_address)
        val mobileNumber: EditText = findViewById(R.id.ed_mobileNumber)
        val gender: EditText = findViewById(R.id.ed_gender)

        val saveUser: Button = findViewById(R.id.button_save_user)

        user = intent.getParcelableExtra("user")

        user?.let {
            userName.setText(it.userName)
            fullName.setText(it.fullName)
            address.setText(it.address)
            mobileNumber.setText(it.mobileNumber)
            gender.setText(it.gender)
        } ?: kotlin.run {

        }

        val repo = UserRepository(this)

        saveUser.setOnClickListener {

            if (userName.text.isNotEmpty() && fullName.text.isNotEmpty() && address.text.isNotEmpty() && mobileNumber.text.isNotEmpty() && gender.text.isNotEmpty()) {

                user?.let {
                    val user = UserModel(
                        userId = it.userId,
                        userName = userName.text.toString(),
                        fullName = fullName.text.toString(),
                        address = address.text.toString(),
                        mobileNumber = mobileNumber.text.toString(),
                        gender = gender.text.toString(),
                    )
                    repo.updateUser(user)
                } ?: kotlin.run {
                    val user = UserModel(
                        userName = userName.text.toString(),
                        fullName = fullName.text.toString(),
                        address = address.text.toString(),
                        mobileNumber = mobileNumber.text.toString(),
                        gender = gender.text.toString(),
                    )
                    repo.insertUser(user)
                }

            } else {
                Toast.makeText(this, "User Not Added : Invalid Input", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

}