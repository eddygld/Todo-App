package edu.towson.cosc435.GLADZAH.todos

import android.media.Image
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Todo (
    @PrimaryKey
    val id: UUID,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "contents")
    val contents: String,

    @ColumnInfo(name = "isCompleted")
    var isCompleted: Boolean,

//    @ColumnInfo(name = "img")
//    val img: String,

    @ColumnInfo(name = "dateCreated")
    val dateCreated: String
) : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
    }
}




