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
    var isJson :Boolean= true

    constructor(parcel: Parcel) : this() {
        link = parcel.readString()
        isJson = parcel.readByte() != 0.toByte()
    }

    fun isEmpty() : Boolean {
        return link.isNullOrEmpty()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(link)
        parcel.writeByte(if (isJson) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Link> {
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