package com.example.urban

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.places.ui.PlacePicker
import kotlinx.android.synthetic.main.activity_new_move.*
import java.io.File

private const val FILENAME = "photo.jpg"
private const val REQUEST_CODE = 42
private lateinit var photoFile: File
private const val PLACE_PICKER_REQUEST = 1
class NewMoveActivity : AppCompatActivity() {

    private var arrs: ArrayList<File> = arrayListOf<File>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_move)
        setupViews()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setupViews(){

        val recyclerView = findViewById<RecyclerView>(R.id.photo_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterPhoto(applicationContext, arrs)
        val buttonOpen = findViewById<Button>(R.id.buttonOpen)
        buttonOpen.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILENAME)
            val fileProvider =
                FileProvider.getUriForFile(this, "com.example.fileprovider", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePictureIntent, REQUEST_CODE)
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        val mapButtonFrom = findViewById<ImageView>(R.id.mapButtonFrom)
        mapButtonFrom.setOnClickListener{
            if(checkSelfPermission(Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                val builder = PlacePicker.IntentBuilder()
                val intent = builder.build(this)
                startActivityForResult(intent, PLACE_PICKER_REQUEST)
            } else {
                requestPermissions(arrayOf(Manifest.permission.INTERNET),  1)
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),  1)
                requestPermissions(arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),  1)


            }
        }

    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            arrs.add(photoFile)
            setupViews()
        } else if (requestCode == PLACE_PICKER_REQUEST && resultCode == Activity.RESULT_OK) {
            val place = PlacePicker.getPlace(data, this)
            textFrom.text = place.address
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}