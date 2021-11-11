package com.android.clienttest.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RandomUser(val gender: String = "", val name:Name, val picture:Picture, val email: String = "", val nat: String = "", val phone: String = ""): Parcelable {
    @Parcelize
    data class Name(val title: String  = "", val first: String = "", val last: String = "") : Parcelable
    @Parcelize
    data class Picture(val large: String = "") : Parcelable
}