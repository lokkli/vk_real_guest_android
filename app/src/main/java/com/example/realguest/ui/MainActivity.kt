package com.example.realguest.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.realguest.R
import com.example.realguest.common.Common.sharedPref
import com.example.realguest.ui.main.VisitsFragment
import com.example.realguest.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getPreferences(Context.MODE_PRIVATE)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnItemSelectedListener { item ->
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