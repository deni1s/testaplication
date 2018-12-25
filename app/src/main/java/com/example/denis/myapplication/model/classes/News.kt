package com.example.denis.myapplication.model.classes

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmModel
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import org.jsoup.Jsoup
import org.simpleframework.xml.Element
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text


@RealmClass
@Root(name = "item", strict = false)
open class News @JvmOverloads constructor(
    @PrimaryKey @field:Path("description")
    @field:Text(required = false)
    @param:Path("description")
    @param:Text(required = false)
    var description: String? = null,

    @field:Path("author")
    @field:Text(required = false)
    @param:Path("author")
    @param:Text(required = false)
    var author: String? = "",

    @field:Path("title")
    @field:Text(required = false)
    @param:Path("title")
    @param:Text(required = false)
    var title: String? = null,

    @field:Path("pubDate")
    @field:Text(required = false)
    @param:Path("pubDate")
    @param:Text(required = false)
    var publishedAt: String? = null,

    @field:Path("content")
    @field:Text(required = false)
    @param:Path("content")
    @param:Text(required = false)
    var content: String? = null,

    @field:Path("link")
    @field:Text(required = false)
    @param:Path("link")
    @param:Text(required = false)
    var url: String? = null,

    @field:Path("image")
    @field:Text(required = false)
    @param:Path("image")
    @param:Text(required = false)
    var image: String? = null,

    var urlToImage: String? = "",

    var source: Source? = Source(),

    @field:Path("category")
    @field:Text(required = false)
    @param:Path("category")
    @param:Text(required = false)
    var artist: String? = null,

    @Ignore var isNewsWatched: Boolean = false,

    @field:Element(name = "guid")
    @param:Element(name = "guid")
    var guid: String? = null

) : RealmModel, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Source::class.java.classLoader),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString()
    ) {
    }

    fun extractImageUrl(): String? {
        if (!urlToImage.isNullOrEmpty()) {
            return urlToImage
        } else if (!description.isNullOrEmpty()) {
            val document = Jsoup.parse(description)
            val imgs = document.select("img")
            for (img in imgs) {
                if (img.hasAttr("src")) {
                    return img.attr("src")
                }
            }
            return ""
        } else {
            return ""
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
        parcel.writeString(url)
        parcel.writeString(image)
        parcel.writeString(urlToImage)
        parcel.writeParcelable(source, flags)
        parcel.writeString(artist)
        parcel.writeByte(if (isNewsWatched) 1 else 0)
        parcel.writeString(guid)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}