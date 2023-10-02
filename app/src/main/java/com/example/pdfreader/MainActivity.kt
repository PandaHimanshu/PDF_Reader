package com.example.pdfreader

import android.app.Activity
import android.app.Instrumentation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.pdfreader.databinding.ActivityMainBinding
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.floatingActionButton2.setOnClickListener{
            binding.textView2.isVisible=false
        launcher.launch("application/pdf")
        }
    }
    private val launcher=registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){
        uri->
        uri?.let {
            binding.pdfview.fromUri(it)
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .scrollHandle( DefaultScrollHandle(this))
                .spacing(10)
                .load()
        }
    }
}