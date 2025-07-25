package com.example.c36a.repository

import com.example.c36a.model.WishlistItemModel
import kotlinx.coroutines.flow.Flow

interface WishlistRepository {
    suspend fun addToWishlist(item: WishlistItemModel)
    fun getWishlistItems(): Flow<List<WishlistItemModel>>
    suspend fun removeFromWishlist(item: WishlistItemModel)
}