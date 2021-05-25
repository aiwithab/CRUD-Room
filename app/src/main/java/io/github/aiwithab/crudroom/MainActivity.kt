package io.github.aiwithab.crudroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.github.aiwithab.crudroom.adapters.ListClickListener
import io.github.aiwithab.crudroom.adapters.UserListAdapter
import io.github.aiwithab.crudroom.add.AddUserActivity
import io.github.aiwithab.crudroom.models.UserModel
import io.github.aiwithab.crudroom.repository.UserRepository

class MainActivity : AppCompatActivity() {

    lateinit var adapter: UserListAdapter
    val repo:UserRepository by lazy {
        UserRepository(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerviewUsers: RecyclerView = findViewById(R.id.recyclerview_users)
        val floatingActionButton: FloatingActionButton = findViewById(R.id.floatingActionButton)

        adapter = UserListAdapter()
        recyclerviewUsers.layoutManager = LinearLayoutManager(this)
        recyclerviewUsers.adapter = adapter

        adapter.setOnItemClick(object : ListClickListener<UserModel> {
            override fun onClick(data: UserModel, position: Int) {
                val intent = Intent(this@MainActivity, AddUserActivity::class.java)
                intent.putExtra("user", data)
                startActivity(intent)
            }

            override fun onDelete(user: UserModel) {
                repo.deleteUser(user)
                fetchUsers()
            }
        })


        floatingActionButton.setOnClickListener {
            val intent = Intent(this@MainActivity,AddUserActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchUsers()
    }

    fun fetchUsers() {
        val allUsers = repo.getAllUsers()
        adapter.setUsers(allUsers)
    }
}