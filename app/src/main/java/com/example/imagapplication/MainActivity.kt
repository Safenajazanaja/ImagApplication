package com.example.imagapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.imagapplication.Dru.loadImageCircle
import com.example.imagapplication.Dru.selectImage
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSelectImage.setOnClickListener {
            selectImage(123)
            // fragment
            // activity || requireActivity()
        }

        btUploadImage.setOnClickListener {
            val imageName = UUID.randomUUID().toString().replace("-", "") + ".jpg"
            val baseUrl = "https://easyfix204.000webhostapp.com/image/"
            val imagePath = baseUrl + imageName
            Log.d(TAG, "onCreate: ${imagePath}")
            Dru.uploadImage(baseContext, baseUrl, imageName, mImageUri) {
                Toast.makeText(baseContext, "${it?.response}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            Log.d(TAG, "onActivityResult: ${data.data}")
            mImageUri = data.data
//            ivImage.setImageURI(Uri.parse(mImageUri))
            ivImage.loadImageCircle(mImageUri.toString())
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}
