package com.elena.escuela.request.model

import com.google.gson.annotations.SerializedName

data class StudentApi (

        var name: Name,
        var email: String,
        var picture: Picture
        /* @SerializedName("picture")var picturesUrls: List<String>?) {
             override fun toString(): String {
                 return super.toString()
             }
         }*/
)

data class Name (
        @SerializedName("first") var name: String,
        @SerializedName("last") var surname: String
)

data class Picture (
        @SerializedName("large") var large: String,
        @SerializedName( "medium") var medium: String,
        @SerializedName("thumbnail") var thumbnail: String
)
