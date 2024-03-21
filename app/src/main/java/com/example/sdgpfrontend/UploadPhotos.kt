package com.example.sdgpfrontend

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView

class UploadPhotos : AppCompatActivity() {

    private val REQ_CODE = 1
    private lateinit var chosenimage: ImageView
    var imguri : Uri? = null


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_photos)

    val choseImageButton = findViewById<Button>(R.id.choosephoto)
        choseImageButton.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent,REQ_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE && resultCode == RESULT_OK && data !=null && data.data !=null){
           imguri = data.data
            val imageview: Bitmap =MediaStore.Images.Media.getBitmap(contentResolver,imguri)
            val chosenImage = findViewById<ImageView>(R.id.chosenimage)
            chosenImage.setImageBitmap(imageview)
        }
    }}


