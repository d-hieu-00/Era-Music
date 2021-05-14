package com.era.eramusic.resources

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import com.era.eramusic.adapters.AllSongAdapter
import com.era.eramusic.adapters.DropdownCategoryAdapter
import com.era.eramusic.adapters.PlaylistAdapter
import com.era.eramusic.database.dbservice
import com.era.eramusic.entitys.Playlist
import com.era.eramusic.entitys.Song
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.util.*


@Suppress("ControlFlowWithEmptyBody", "UNCHECKED_CAST")
class Resource () {
    companion object{
        private var SharedPre: SharedPreferences?= null
        var data = Data()
        var adapters = MsgAdapter()
        val mediaPlayer = MediaPlayer()
        @SuppressLint("StaticFieldLeak")
        var con: Activity? = null

        fun GetSharedPreferences (context: Context) {
            val gson = GsonBuilder().create()
            SharedPre = context.getSharedPreferences("Resource", AppCompatActivity.MODE_PRIVATE)
            if (SharedPre == null) {
                SharedPre?.edit()?.apply()
            }
            val _data = SharedPre?.getString("data", "")
            val _adapters = SharedPre?.getString("adapters", "")
            if (_data == "" || _adapters == "")
                return
            data = gson.fromJson(_data, Data::class.java)
            adapters = gson.fromJson(_adapters, MsgAdapter::class.java)

        }
        fun SaveSharedPreferences () {
            val gson = GsonBuilder().create()
            SharedPre?.edit()?.putString("data", gson.toJson(data))?.apply()
            SharedPre?.edit()?.putString("adapters", gson.toJson(adapters))?.apply()
        }
        fun CheckNullSharedPreferences () :Boolean {
            return data.user == null
        }

        fun LoadListSong() {
            data.getAllSong()
            //if (Resource.adapters.SongAdapter == null)
                Resource.adapters.SongAdapter = AllSongAdapter(
                    con!!,
                    R.layout.simple_gallery_item,
                    data.arrSong)
        }

        fun LoadListSongByPagram(NameSong: String, NameArt: String, NameCategory: String) {
            data.getAllSongByPagram(NameSong, NameArt, NameCategory)
            //if (Resource.adapters.SongAdapter == null)
            adapters.SongAdapter = AllSongAdapter(
                con!!,
                R.layout.simple_gallery_item,
                data.arrSong)
        }

        fun LoadListTypeSong() {
            data.getAllCategory()
            //while (data.arrTypeSong.size <= 0) { }
            adapters.DropDownCategoryAdapter = DropdownCategoryAdapter(
                con!!,
                R.layout.simple_gallery_item,
                data.arrTypeSong)
        }

        fun LoadListPlaylist(id: String?) {
            data.getAllPlaylistById(id!!)
            //val arr = object {val arr : data.arrPlaylist}
            adapters.PlaylistAdapter = PlaylistAdapter(
                con!!,
                R.layout.simple_gallery_item,
                data.arrPlaylist.clone() as List<Playlist>)
        }

        fun InsertPlaylist(Name: String) : Boolean {
            if (data.InsertPlaylist(Name))
                return true;
            return false;
        }

        fun Login(Account: String, Passord: String) : Boolean {
            if (data.login(Account, Passord))
            {
                LoadListPlaylist(data.user?.id!!)
                return true;
            }
            return false;
        }

        @Suppress("ControlFlowWithEmptyBody")
        fun LoadData (activity: Activity) {
            con = activity

            LoadListTypeSong()
            if (Resource.data.user != null) {
                LoadListPlaylist(data.user?.id)
            }
        }
    }
}