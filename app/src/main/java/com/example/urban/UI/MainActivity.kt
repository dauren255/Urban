package com.example.urban.UI

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urban.Adapter.AdapterDashboardImage
import com.example.urban.Adapter.AdapterOrder
import com.example.urban.Adapter.AdapterPhoto
import com.example.urban.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViews()
    }

    fun setupViews(){

        val recyclerView = findViewById<RecyclerView>(R.id.mainPageActualRecyclerView)
        val arrs = arrayListOf("asd" )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterOrder(applicationContext, arrs)

        val recyclerViewHistory = findViewById<RecyclerView>(R.id.mainPageHistoryRecyclerView)
        val arrsHistory = arrayListOf("asd", "asd" )
        recyclerViewHistory.layoutManager = LinearLayoutManager(this)
        recyclerViewHistory.adapter = AdapterOrder(applicationContext, arrs)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        bottomNavigationView.setSelectedItemId(R.id.list)

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
                R.id.list -> return@OnNavigationItemSelectedListener true
                R.id.account -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            ViewList::class.java
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