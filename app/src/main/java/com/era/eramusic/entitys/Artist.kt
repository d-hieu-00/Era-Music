package com.era.eramusic.entitys

import com.google.gson.annotations.SerializedName

class Artist(ID: String?,
             Name: String?,
             LinkPic: String?) {
    @SerializedName("id")
    var id: String? = ID
    @SerializedName("name")
    var name: String? = LinkPic
    @SerializedName("linkPic")
    var linkPic: String? = LinkPic
}