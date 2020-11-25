package com.kotlin.cccandroidtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kotlin.cccandroidtest.databinding.ActivityMainBinding
import com.kotlin.cccandroidtest.ui.MainFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val fragment = MainFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, fragment, fragment.javaClass.simpleName)
            .addToBackStack(null).commit()
    }
}