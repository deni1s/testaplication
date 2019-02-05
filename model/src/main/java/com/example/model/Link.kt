package com.example.model

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class Link() : RealmModel, Parcelable {

    @PrimaryKey
    var link :String = ""
    var type :Int= NOT_VALID_TYPE

    constructor(parcel: Parcel) : this() {
        link = parcel.readString()
        type = parcel.readInt()
    }

    constructor(url : String, type : Int) : this() {
        this.link = url
        this.type = type
    }

    fun isEmpty() : Boolean {
        return link.isNullOrEmpty()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(link)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Link> {

        val JSON_TYPE = 0
        val XML_TYPE = 1
        val NOT_VALID_TYPE = -1
        override fun createFromParcel(parcel: Parcel): Link {
            return Link(parcel)
        }

        override fun newArray(size: Int): Array<Link?> {
            return arrayOfNulls(size)
        }
    }

    override fun equals(other: Any?): Boolean {
        return other != null && other is Link && other.link.equals(link)
    }
}