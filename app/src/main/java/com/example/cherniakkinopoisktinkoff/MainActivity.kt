package com.example.cherniakkinopoisktinkoff

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cherniakkinopoisktinkoff.presentation.list.FilmListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val startFragment = FilmListFragment()

            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, startFragment, START_FRAGMENT_TAG)
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }

    companion object {
        private const val START_FRAGMENT_TAG = "tag_st_fr"
    }
}