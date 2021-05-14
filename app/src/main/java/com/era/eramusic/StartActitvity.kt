package com.era.eramusic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class StartActitvity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LoadDataAsyncTask(this).execute()
    }
}