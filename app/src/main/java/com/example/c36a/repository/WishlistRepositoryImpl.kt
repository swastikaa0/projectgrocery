package com.example.c36a.repository

import com.example.c36a.model.WishlistItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object WishlistRepositoryImpl : WishlistRepository {

    private val wishlist = mutableListOf<WishlistItemModel>()
    private val wishlistFlow = MutableStateFlow<List<WishlistItemModel>>(emptyList())

    override suspend fun addToWishlist(item: WishlistItemModel) {
        if (wishlist.none { it.productName == item.productName }) {
            wishlist.add(item)
            wishlistFlow.value = wishlist.toList()
        }
    }

    override fun getWishlistItems(): Flow<List<WishlistItemModel>> = wishlistFlow

    override suspend fun removeFromWishlist(item: WishlistItemModel) {
        wishlist.removeAll { it.productName == item.productName }
        wishlistFlow.value = wishlist.toList()
    }
}