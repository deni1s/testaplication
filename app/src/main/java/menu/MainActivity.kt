package com.example.app.menu

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.app.R
import com.example.presentation.NewsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView: ImageView = findViewById(R.id.news_image)
        imageView.animate().rotationBy(360f).setDuration(1000).withEndAction {
            val intent = Intent(applicationContext, NewsActivity::class.java)
            startActivity(intent)
        }
    }
}
