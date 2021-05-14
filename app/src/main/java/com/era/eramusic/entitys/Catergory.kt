package com.era.eramusic.entitys

import com.google.gson.annotations.SerializedName

class Catergory (ID: Int?,
                 Name: String?,
                 ArrSongID: List<Int>?) {
    @SerializedName("id")
    var id = ID
    @SerializedName("name")
    var name = Name
    var arrSongID = ArrSongID
}