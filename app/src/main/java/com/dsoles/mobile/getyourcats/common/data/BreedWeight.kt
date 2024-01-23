package com.dsoles.mobile.getyourcats.modules.home.data

import com.google.gson.annotations.SerializedName


data class BreedWeight(
    @SerializedName("imperial") var imperial: String? = null,
    @SerializedName("metric") var metric: String? = null,
)