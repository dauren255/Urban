package com.example.urban.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urban.Adapter.AdapterCompany
import com.example.urban.Adapter.AdapterDashboardImage
import com.example.urban.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setupViews()
    }

    fun setupViews() {
        setNavigationBar()

        val newMoveButton = findViewById<Button>(R.id.newMoveButton)
        newMoveButton.setOnClickListener {
            val intent = Intent(this, NewMoveActivity::class.java)
            startActivity(intent)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.dashboardPicturesRecyclerView)
        val arrs = arrayListOf("asd", "asd" )
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true)
        recyclerView.adapter = AdapterDashboardImage(applicationContext, arrs)

        val recyclerViewCompanies = findViewById<RecyclerView>(R.id.dashboardCompaniesRecyclerView)
        recyclerViewCompanies.layoutManager = LinearLayoutManager(this)
        recyclerViewCompanies.adapter = AdapterCompany(applicationContext, arrs)

    }


    private fun setNavigationBar() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        bottomNavigationView.setSelectedItemId(R.id.menu)

        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu -> return@OnNavigationItemSelectedListener true
                R.id.list -> {
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.account -> {
                    startActivity(Intent(applicationContext, ViewList::class.java))
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}

