package com.mmh.feedbackapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mmh.feedbackapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (supportFragmentManager.backStackEntryCount != 0) {
            supportFragmentManager.beginTransaction().replace(R.id.container_view, CustomerFragment()).addToBackStack(null).commit()
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.container_view, StartFragment()).commit()
        }
    }
}