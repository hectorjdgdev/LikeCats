package com.dsoles.mobile.getyourcats.common.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BreedEntry(
    @PrimaryKey var id: String,
    var name: String? = null,
    var breedImageUrl: String? = null,
    var origin: String? = null,
    var temperament: String? = null,
    var description: String? = null
)