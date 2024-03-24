package com.example.sdgpfrontendnew

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.UUID

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
        val uploadingImage =  findViewById<Button>(R.id.uploadohotos)
        uploadingImage.setOnClickListener {
            if (imguri != null){
                imageUpload()
            }else{
                Toast.makeText(this,"Please Select an image",Toast.LENGTH_LONG).show()
            }
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
    }
    private  fun imageUpload(){

        val ref : StorageReference = FirebaseStorage.getInstance().reference.child("images/${UUID .randomUUID()}.jpg")


        imguri?.let {
            ref.putFile(it).addOnSuccessListener {

                Toast.makeText(this,"Image Uploaded Successfully", Toast.LENGTH_LONG).show()
            }
                .addOnFailureListener{

                Toast.makeText(this,"Image Upload Failed", Toast.LENGTH_LONG).show()
            }
        }

    }
}