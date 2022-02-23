package com.example.realguest.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.realguest.R
import com.example.realguest.common.Common.sharedPref
import com.example.realguest.databinding.ActivityMainBinding
import com.example.realguest.ui.main.VisitsFragment
import com.example.realguest.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        sharedPref = getPreferences(Context.MODE_PRIVATE)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> supportFragmentManager.commit {
                    replace(R.id.fragment_container_view, VisitsFragment())
                }
                R.id.person -> supportFragmentManager.commit {
                    replace(R.id.fragment_container_view, ProfileFragment())
                }
                R.id.settings -> ""
            }
            true
        }
    }
}