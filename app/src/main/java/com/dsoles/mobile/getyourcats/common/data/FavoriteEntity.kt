package com.dsoles.mobile.getyourcats.common.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEntity(
    @PrimaryKey var id: String,
    var name: String,
    var breedImageUrl: String? = null
)