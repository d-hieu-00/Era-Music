package com.era.eramusic.handletest

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.os.Build
import android.widget.*
import androidx.annotation.RequiresApi
import com.era.eramusic.R
import com.era.eramusic.adapters.PlaylistAdapter
import com.era.eramusic.entitys.Playlist
import com.era.eramusic.entitys.Song
import com.era.eramusic.resources.Resource
import java.io.IOException


@SuppressLint("SetTextI18n")
class TestMenu (context: Context){
    val con: Context = context


    fun Login() {
        (con as Activity).setContentView(R.layout.menutest_login)
        val acc = con.findViewById<EditText>(R.id.editAccount)
        val pass = con.findViewById<EditText>(R.id.editPassword)
        val btnLogin = con.findViewById<Button>(R.id.btnLogin)
        val btnLogout = con.findViewById<Button>(R.id.btnLogout)
        val content = con.findViewById<TextView>(R.id.content)

        if (content.text.toString() == "")
        {
            if (Resource.data.user != null) {
                btnLogin.isEnabled = false
                btnLogout.isEnabled = true
                content.text = "Đã đăng nhập với tài khoản: ${Resource.data.user?.account}"
            }
            else {
                btnLogin.isEnabled = true
                btnLogout.isEnabled = false
                content.text = "Chưa đăng nhập"
            }
        }

        btnLogin.setOnClickListener {
            val accS = acc.text.toString()
            val passS = pass.text.toString()
            if (accS == "" || passS == ""){
                Toast.makeText(con, "Tài khoản mật khẩu không được trống", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Resource.Login(accS, passS)) {
                Toast.makeText(con, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                content.text = "Đã đăng nhập với tài khoản: ${Resource.data.user?.account}"
                btnLogin.isEnabled = false
                btnLogout.isEnabled = true
                Resource.SaveSharedPreferences()
                return@setOnClickListener
            }
            Toast.makeText(con, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show()
        }

        btnLogout.setOnClickListener {
            if (Resource.data.user != null) {
                Resource.data.user = null
                Resource.data.arrPlaylist = arrayListOf<Playlist>()
                Resource.adapters.PlaylistAdapter = null
                Resource.SaveSharedPreferences()
                btnLogin.isEnabled = true
                btnLogout.isEnabled = false
                content.text = "Chưa đăng nhập"
                Toast.makeText(con, "Đăng xuất thành công", Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun AllMusic(){
        (con as Activity).setContentView(R.layout.menutest_allsong)
        val content = con.findViewById<TextView>(R.id.content)
        val gridView = con.findViewById<GridView>(R.id.MyGridView)
        val spin = con.findViewById<Spinner>(R.id.spinnerNameCategory)
        val btnSearch = con.findViewById<Button>(R.id.btnSearch)

        gridView.adapter = Resource.adapters.SongAdapter
        spin.adapter = Resource.adapters.DropDownCategoryAdapter

        btnSearch.setOnClickListener {
            val nameSong = con.findViewById<TextView>(R.id.editNameSong).text.toString()
            val nameArtsit = con.findViewById<TextView>(R.id.EditNameArtist).text.toString()
            val nameCategory = Resource.data.arrTypeSong.get(spin.selectedItemPosition).name

            Resource.LoadListSongByPagram(nameSong, nameArtsit, nameCategory!!)
            gridView.adapter = Resource.adapters.SongAdapter
            return@setOnClickListener
        }

        gridView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val s = parent.getItemAtPosition(position) as Song
            //Resource.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            try {
                //Resource.mediaPlayer.setDataSource("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
//                Resource.mediaPlayer.setDataSource(s.linkMusic)
                // below line is use to prepare
                // and start our media player.
                //Resource.mediaPlayer.prepare()
                //Resource.mediaPlayer.start()
                Resource.mediaPlayer.apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource("https://man1412.000webhostapp.com/SOFAR-Binz.mp3")
                    //prepare() // might take long! (for buffering, etc)
                    start()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            // below line is use to display a toast message.
            // below line is use to display a toast message.
            Toast.makeText(con, "${s.name} started playing..", Toast.LENGTH_SHORT).show()
            true
        }
    }

    fun Playlist(){
        val ac :Activity = con as Activity
        ac.setContentView(R.layout.menutest_playlist)

        val listView = con.findViewById<ListView>(R.id.MyListView)
        val btnAdd = con.findViewById<Button>(R.id.btnAdd)
        val btnSearch = con.findViewById<Button>(R.id.btnSearch)
        val editName = con.findViewById<EditText>(R.id.namePlaylist)

        if (Resource.adapters.PlaylistAdapter == null) {
            Resource.adapters.PlaylistAdapter = PlaylistAdapter(
                con,
                android.R.layout.simple_gallery_item,
                Resource.data.arrPlaylist)
        }
        listView.adapter = Resource.adapters.PlaylistAdapter
        btnAdd.setOnClickListener {
            val name = editName.text.toString()
            if (name == "") {
                Toast.makeText(con, "Tên playlist không được rỗng", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (Resource.InsertPlaylist(name)) {
                Toast.makeText(con, "Thêm thành công", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(con, "Lỗi khi thêm playlist", Toast.LENGTH_SHORT).show()
        }
        btnSearch.setOnClickListener {
            val name = editName.text.toString().toLowerCase()
            if (name == "") {
                Toast.makeText(con, "Tên playlist không được rỗng", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            var i = 0
            while(i < Resource.data.arrPlaylist.size){
                val value = Resource.data.arrPlaylist.get(i)
                if (value.name?.toLowerCase()?.contains(name)!!) {
                    Resource.adapters.PlaylistAdapter?.remove(value)
                    Resource.adapters.PlaylistAdapter?.add(value)
                }
                else
                    Resource.adapters.PlaylistAdapter?.remove(value)
                i++
            }
        }
    }

    fun LoadFromStorage(){
        val ac :Activity = con as Activity
        ac.setContentView(R.layout.menutest_loadfromstorage)
    }

    fun ControlMedia(){
        val ac :Activity = con as Activity
        ac.setContentView(R.layout.menutest_controlmedia)
    }

    fun Album(){
        val ac :Activity = con as Activity
        ac.setContentView(R.layout.menutest_album)
    }
}