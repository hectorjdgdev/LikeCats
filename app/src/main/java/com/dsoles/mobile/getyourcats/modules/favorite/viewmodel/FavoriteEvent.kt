package com.dsoles.mobile.getyourcats.modules.favorite.viewmodel

import com.dsoles.mobile.getyourcats.common.data.BreedEntry


interface FavoriteEventHandler {
    fun onEvent(event: FavoriteEvent)
}

sealed class FavoriteEvent {
    data class FavoriteAddClicked(
        val breed: BreedEntry
    ) : FavoriteEvent()

    data class FavoriteRemoveClicked(val breed: BreedEntry) : FavoriteEvent()
}