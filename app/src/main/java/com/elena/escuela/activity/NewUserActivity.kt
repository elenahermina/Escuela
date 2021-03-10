package com.elena.escuela.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.elena.escuela.viewmodel.NewUserActivityViewModel

import com.elena.escuela.databinding.CreateUserBinding
import kotlinx.coroutines.launch

class NewUserActivity: AppCompatActivity() {
    lateinit var binding: CreateUserBinding
    private lateinit var model: NewUserActivityViewModel

    private var bitmap : Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CreateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this).get(NewUserActivityViewModel::class.java)

        binding.bDownload.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(intent)
        }


        binding.buttonGuardar.setOnClickListener {
            lifecycleScope.launch {
                model.insertarUsuario(
                        binding.editTextTextEmailAddress.text.toString(),
                        binding.name.text.toString(),
                        binding.surename.text.toString(),
                        bitmap)
                finish()

            }


        }

    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data
            uri?.let { uri ->
                contentResolver?.let { contentResolver ->
                    bitmap = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                        val source = ImageDecoder.createSource(contentResolver, uri)
                        ImageDecoder.decodeBitmap(source)
                    } else {
                        MediaStore.Images.Media.getBitmap( this.contentResolver, uri)
                    }
                }
            }
            binding.newPhoto.setImageBitmap(bitmap) //  handle chosen image
        }
    }
}