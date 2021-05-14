package com.era.eramusic.resources

import android.R
import com.era.eramusic.adapters.AllSongAdapter
import com.era.eramusic.database.dbservice
import com.era.eramusic.entitys.*
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.io.IOException

@Suppress("ControlFlowWithEmptyBody")
class Data () {
    @SerializedName("ListSong")
    var arrSong: ArrayList<Song> = arrayListOf<Song>()
    @SerializedName("ListTypeSong")
    var arrTypeSong: ArrayList<Catergory> = arrayListOf<Catergory>()
    @SerializedName("ListPlaylist")
    var arrPlaylist: ArrayList<Playlist> = arrayListOf<Playlist>()
    @SerializedName("User")
    var user: User? = null

    fun getAllSong () {
        val request = Request.Builder().url(dbservice.getAllSong).build()
        val client = OkHttpClient()
        var wait = true
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                arrSong = gson.fromJson(body, Data::class.java).arrSong
                wait = false
            }
            override fun onFailure(call: Call, e: IOException) {
                println("fail to get all song")
                wait = false
            }
        })
        while (wait) { }
    }

    fun getAllSongByPagram (NameSong: String, NameArt: String, NameCategory: String) {
        var wait = true
        val request = Request.Builder()
            .url(dbservice.getSongByPagram + "namesong=$NameSong&nameart=$NameArt&typesong=$NameCategory")
            .build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                arrSong = gson.fromJson(body, Data::class.java).arrSong
                wait = false
            }
            override fun onFailure(call: Call, e: IOException) {
                println("fail to get song by pargram")
                wait = false
            }
        })
        while (wait) {  }
    }

    fun getAllCategory () {
        val request = Request.Builder().url(dbservice.getAllTypeSong).build()
        val client = OkHttpClient()
        var wait = true
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                arrTypeSong = gson.fromJson(body, Data::class.java).arrTypeSong
                wait = false
            }
            override fun onFailure(call: Call, e: IOException) {
                println("fail to get all type song")
                wait = false
            }
        })
        while (wait) { }
    }

    fun getAllPlaylistById (id: String) {
        val request = Request.Builder().url(dbservice.getAllPlaylist + "id=$id").build()
        val client = OkHttpClient()
        var wait = true
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                arrPlaylist = gson.fromJson(body, Data::class.java).arrPlaylist
                wait = false
            }
            override fun onFailure(call: Call, e: IOException) {
                println("fail to get playlist by id $id")
                wait = false
            }
        })
        while (wait) { }
    }

    fun InsertPlaylist (name: String) : Boolean {
        val request = Request.Builder()
            .url(dbservice.insertPlaylist + "idnd=${user?.id}&name=$name")
            .build()
        val client = OkHttpClient()
        var playlist: Playlist? = null
        var wait = true
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                try {
                    playlist = gson.fromJson(body, Playlist::class.java)
                    wait = false
                } catch (e: Exception) {
                    wait = false
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("fail to insert playlist $name")
                wait = false
            }
        })
        while (wait) { }
        if (playlist != null){
            arrPlaylist.add(playlist!!)
            Resource.adapters.PlaylistAdapter?.add(playlist)
            return true
        }
        return false
    }

    fun login (Account: String, Password: String) : Boolean {
        val request = Request.Builder()
            .url(dbservice.login + "account=$Account&password=$Password")
            .build()
        val client = OkHttpClient()
        var wait = true
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                val gson = GsonBuilder().create()
                try {
                    user = gson.fromJson(body, Data::class.java).user
                    wait = false
                } catch (e: Exception) {
                    user = null
                    wait = false
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("fail to get playlist by account $Account")
                wait = false
            }
        })
        while (wait) { }
        if (user == null)
            return false
        return true
    }
}