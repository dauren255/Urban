package com.example.urban

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*



class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setupViews()
    }

    fun setupViews(){
        setNavigationBar()

        newMoveButton.setOnClickListener{
            val intent = Intent(this, NewMoveActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setNavigationBar() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_bar)

        bottomNavigationView.setSelectedItemId(R.id.dashboard)

        bottomNavigationView.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            MainActivity::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
                R.id.dashboard -> return@OnNavigationItemSelectedListener true
                R.id.about -> {
                    startActivity(
                        Intent(
                            applicationContext,
                            About::class.java
                        )
                    )
                    overridePendingTransition(0, 0)
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })    }
}