package org.wit.recordshop.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecordModel(var id: Long = 0,
                       var title: String = "",
                       var description: String = "",
                       var genre: String = "") : Parcelable



