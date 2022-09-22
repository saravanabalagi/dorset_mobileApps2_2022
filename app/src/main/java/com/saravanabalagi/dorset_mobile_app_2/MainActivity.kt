package com.saravanabalagi.dorset_mobile_app_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.commit
import com.saravanabalagi.dorset_mobile_app_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)

        binding.helloWorldTextView.text = getString(R.string.hello_android)
        Log.i("MainActivity", binding.helloWorldTextView.text.toString())

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.main_fragment, MainFragment().apply {
                arguments = Bundle().apply {
                    putString("NEW_TEXT", "Text Changed in Fragment")
                }
            })
        }
    }
}