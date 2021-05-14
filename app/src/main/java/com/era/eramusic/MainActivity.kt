package com.era.eramusic

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.era.eramusic.handletest.TestMenu
import com.era.eramusic.resources.Resource
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menutest_login)
        TestMenu(this).Login()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.test_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val t = TestMenu(this)
        return when (item.itemId) {
            R.id.login -> {
                t.Login()
                true
            }
            R.id.allmusic -> {
                t.AllMusic()
                true
            }
            R.id.playlist -> {
                t.Playlist()
                true
            }
            R.id.loadfromstorage -> {
                t.LoadFromStorage()
                true
            }
            R.id.controlmedia -> {
                t.ControlMedia()
                true
            }
            R.id.album -> {
                t.Album()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}