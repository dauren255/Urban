package com.example.urban.UI

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.urban.R
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*

class NewMovePickPlacesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_places)
        setupViews()
        val apiKey = getString(R.string.api_key)
        if (!Places.isInitialized()){
            Places.initialize(this, apiKey)
        }

        val placesClient = Places.createClient(this)

        val autocompleteSupportFragment = supportFragmentManager.findFragmentById(R.id.autocompleteFrom) as AutocompleteSupportFragment
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG, Place.Field.NAME))
        autocompleteSupportFragment.setOnPlaceSelectedListener(
            object : PlaceSelectionListener {
                override fun onPlaceSelected(place: Place) {
                    val latLng = place.latLng
                    Toast.makeText(this@NewMovePickPlacesActivity, "" + latLng!!.latitude, Toast.LENGTH_SHORT).show()
                }

                override fun onError(status: Status) {
                    Toast.makeText(this@NewMovePickPlacesActivity, "" + status.getStatusMessage(), Toast.LENGTH_SHORT).show()
                }
            })

    }

    fun setupViews(){


    }
}