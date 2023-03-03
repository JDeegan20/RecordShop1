package org.wit.recordshop.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecordModel(var title: String = "",
                       var description: String = "",
                       var genre: String = ""
                        ) : Parcelable



