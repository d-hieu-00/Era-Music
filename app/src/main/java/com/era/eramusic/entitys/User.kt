package com.era.eramusic.entitys

import com.google.gson.annotations.SerializedName

class User (ID: String?,
            Name: String?,
            Mail: String?,
            Age: String?,
            Account: String?,
            Password: String?,
            Link_Pic: String?){
    @SerializedName("id")
    var id = ID
    @SerializedName("name")
    var name = Name
    @SerializedName("mail")
    var mail = Mail
    @SerializedName("age")
    var age = Age
    @SerializedName("account")
    var account = Account
    @SerializedName("password")
    var password = Password
    @SerializedName("linkPic")
    var linkPic = Link_Pic
}