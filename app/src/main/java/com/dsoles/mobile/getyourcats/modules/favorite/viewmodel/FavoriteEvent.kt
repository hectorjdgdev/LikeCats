package com.dsoles.mobile.getyourcats.modules.favorite.viewmodel


interface FavoriteEventHandler {
    fun onEvent(event: FavoriteEvent)
}

sealed class FavoriteEvent {
    data class FavoriteAddClicked(
        val id: String,
        val name: String,
        val imageUrl: String
    ) : FavoriteEvent()

    data class FavoriteRemoveClicked(val id: String) : FavoriteEvent()
}