package com.era.eramusic.database

import com.era.eramusic.entitys.Song
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import okhttp3.*
import java.io.IOException

class dbservice {
    companion object{
        const val getAllSong = "https://era-mobile.000webhostapp.com/api/getAllSong"
        const val getSongByPagram = "https://era-mobile.000webhostapp.com/api/getSongByPagram?"
        const val getAllTypeSong = "https://era-mobile.000webhostapp.com/api/getAllTypeSong"
        const val getAllPlaylist = "https://era-mobile.000webhostapp.com/api/getAllPlaylist?"
        const val insertPlaylist = "https://era-mobile.000webhostapp.com/api/insertPlaylist?"
        const val login = "https://era-mobile.000webhostapp.com/api/login?"
    }
}