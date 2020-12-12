package com.example.urban.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urban.Adapter.AdapterPhoto
import com.example.urban.Adapter.AdapterViewList
import com.example.urban.R
import com.example.urban.model.ViewListItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class ViewList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_list)
        setupViews()
    }
    fun setupViews(){
        val recyclerView = findViewById<RecyclerView>(R.id.view_list_recycler_view)

        val arrs = mutableListOf(
            ViewListItem(R.drawable.account,"Настройки")
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            AdapterViewList(applicationContext, arrs)
        setNavigationBar()
    }

    fun setNavigationBar(){
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        bottomNavigationView.setSelectedItemId(R.id.account)

        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            Dashboard::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account -> return@OnNavigationItemSelectedListener true
                R.id.list -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            MainActivity::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}