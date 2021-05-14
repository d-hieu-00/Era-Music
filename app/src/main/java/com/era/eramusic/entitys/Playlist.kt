package com.era.eramusic.entitys

import com.google.gson.annotations.SerializedName

class Playlist (ID: Int?,
                Name: String?,
                ArrSongID: List<Int>?) {
    @SerializedName("id")
    var id: Int? = ID
    @SerializedName("name")
    var name: String? = Name
    var arrSongID: List<Int>? = ArrSongID
}