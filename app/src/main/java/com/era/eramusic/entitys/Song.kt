package com.era.eramusic.entitys

import com.google.gson.annotations.SerializedName

class Song(ID: String?,
           Name: String?,
           DateRelease: String?,
           DateUpload: String?,
           Like: String?,
           LinkPic: String?,
           LinkMusic: String?,
           Artist: String?,
           Category: String?){
    @SerializedName("id")
    var id = ID
    @SerializedName("name")
    var name = Name
    @SerializedName("dateRelease")
    var dateRelease = DateRelease
    @SerializedName("dateUpload")
    var dateUpload = DateUpload
    @SerializedName("like")
    var like = Like
    @SerializedName("linkPic")
    var linkPic = LinkPic
    @SerializedName("linkMusic")
    var linkMusic = LinkMusic
    @SerializedName("artist")
    var artist = Artist
    @SerializedName("catergory")
    var catergory = Category
}