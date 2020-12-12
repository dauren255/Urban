package com.example.urban.UI

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.urban.Adapter.AdapterPhoto
import com.example.urban.R
import java.io.File


private const val FILENAME = "photo.jpg"
private const val REQUEST_CODE = 42
private lateinit var photoFile: File

class NewMoveActivity : AppCompatActivity() {

    private var arrs: ArrayList<File> = arrayListOf<File>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_move)
        setupViews()

    }
    fun setupViews() {

        val recyclerView = findViewById<RecyclerView>(R.id.photo_recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            AdapterPhoto(applicationContext, arrs)
        val buttonOpen = findViewById<Button>(R.id.buttonOpen)
        buttonOpen.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            photoFile = getPhotoFile(FILENAME)
            val fileProvider =
                FileProvider.getUriForFile(this, "com.example.fileprovider",
                    photoFile
                )
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)
            if (takePictureIntent.resolveActivity(this.packageManager) != null) {
                startActivityForResult(takePictureIntent,
                    REQUEST_CODE
                )
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }
        }
        val buttonForPickPlace = findViewById<Button>(R.id.buttonForPickPlace)
        buttonForPickPlace.setOnClickListener{
            startActivity(Intent(applicationContext, NewMovePickPlacesActivity::class.java))
        }

    }

    private fun getPhotoFile(fileName: String): File {
        val storageDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(fileName, ".jpg", storageDirectory)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            arrs.add(photoFile)
            setupViews()
       } else {
            super.onActivityResult(requestCode, resultCode, data)
       }
    }
}

