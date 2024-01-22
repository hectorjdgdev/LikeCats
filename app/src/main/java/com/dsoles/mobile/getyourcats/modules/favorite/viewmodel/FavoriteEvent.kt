package com.dsoles.mobile.getyourcats.modules.favorite.viewmodel


interface FavoriteEventHandler {
    fun onEvent(event: FavoriteEvent)
}

sealed class FavoriteEvent {
    data class FavoriteAddClicked(
        val id: String,
        val name: String,
        val breedImageUrl: String,
        val origin: String,
        val temperament: String,
        val description: String
    ) : FavoriteEvent()

    data class FavoriteRemoveClicked(val id: String) : FavoriteEvent()
}