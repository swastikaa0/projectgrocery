package com.example.c36a.repository

import com.example.c36a.model.ProductModel


    interface ProductRepository {
        fun addProduct(product: ProductModel,
                       callback: (Boolean, String) -> Unit)

        fun updateProduct(productId: String, data: MutableMap<String,Any?>,
                          callback: (Boolean, String) -> Unit)

        fun deleteProduct(productId: String,
                          callback: (Boolean, String) -> Unit)

        fun getProductById(productId: String,
                           callback: (ProductModel?, Boolean, String) -> Unit)

        fun getAllProducts(callback: (List<ProductModel>?, Boolean, String) -> Unit)

        fun searchProducts(query: String,
                           callback: (List<ProductModel>?, Boolean, String) -> Unit)

        fun getProductsByCategory(category: String,
                                  callback: (List<ProductModel>?, Boolean, String) -> Unit)

        fun getProductsByPriceRange(minPrice: Int, maxPrice: Int,
                                    callback: (List<ProductModel>?, Boolean, String) -> Unit)

        fun getProductsByAvailability(isAvailable: Boolean,
                                      callback: (List<ProductModel>?, Boolean, String) -> Unit)

        fun getProductsByRating(rating: Double,
                                callback: (List<ProductModel>?, Boolean, String) -> Unit)

        fun getProductsByDiscount(discount: Double,
                                  callback: (List<ProductModel>?, Boolean, String) -> Unit)

    }

