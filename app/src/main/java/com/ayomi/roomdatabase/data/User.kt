package com.ayomi.roomdatabase.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @NotNull @ColumnInfo(name = "first_name") val firstName: String,
    @NotNull @ColumnInfo(name = "last_name") val lastName: String,
    @NotNull @ColumnInfo(name = "age") val age: Int
) : Parcelable
