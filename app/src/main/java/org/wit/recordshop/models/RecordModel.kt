package org.wit.recordshop.models

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecordModel(var id: Long = 0,
                       var title: String = "",
                       var description: String = "",
                       var genre: String = "",
                       var image: Uri = Uri.EMPTY,
                       var lat : Double = 0.0,
                       var lng: Double = 0.0,
                       var zoom: Float = 0f) : Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable






