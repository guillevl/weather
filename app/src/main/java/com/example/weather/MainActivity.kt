package com.example.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weather.climas.WeathersFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, WeathersFragment()).commit()
    }
}