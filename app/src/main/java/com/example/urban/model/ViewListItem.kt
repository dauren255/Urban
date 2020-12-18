package com.example.urban.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ViewListItem (val icon: Int,
                    val itemname: String
): Parcelable {}