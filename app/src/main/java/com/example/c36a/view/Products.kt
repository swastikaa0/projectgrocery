package com.example.c36a.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c36a.R

data class Product(
    val id: Int = 0,
    val name: String,
    val price: String,
    val unit: String = "/KG",
    val rating: Float = 4.0f,
    val imageRes: Int,
    var isFavorite: Boolean = false,
    val category: String = "Fruit"
)

@Composable
fun Products() {
    val products = remember {
        mutableStateListOf(
            // Fruits (5 items)
            Product(1, "Orange", "$2.99", "/KG", 4.0f, R.drawable.apple_image, category = "Fruit"),
            Product(2, "Banana", "$1.00", "/KG", 4.0f, R.drawable.apple_image, category = "Fruit"),
            Product(3, "Strawberry", "$3.90", "/KG", 4.8f, R.drawable.apple_image, category = "Fruit"),
            Product(4, "Apple", "$2.50", "/KG", 4.5f, R.drawable.apple_image, category = "Fruit"),
            Product(5, "Mango", "$3.75", "/KG", 4.7f, R.drawable.apple_image, category = "Fruit"),

            // Vegetables (5 items)
            Product(6, "Carrot", "$1.80", "/KG", 4.2f, R.drawable.apple_image, category = "Vegetable"),
            Product(7, "Tomato", "$2.20", "/KG", 4.1f, R.drawable.apple_image, category = "Vegetable"),
            Product(8, "Broccoli", "$3.50", "/KG", 4.3f, R.drawable.apple_image, category = "Vegetable"),
            Product(9, "Bell Pepper", "$4.25", "/KG", 4.4f, R.drawable.apple_image, category = "Vegetable"),
            Product(10, "Spinach", "$2.80", "/bunch", 4.0f, R.drawable.apple_image, category = "Vegetable"),

            // Bakery Items (5 items)
            Product(11, "White Bread", "$2.50", "/loaf", 4.1f, R.drawable.apple_image, category = "Bakery"),
            Product(12, "Croissant", "$1.75", "/piece", 4.6f, R.drawable.apple_image, category = "Bakery"),
            Product(13, "Bagel", "$0.85", "/piece", 4.2f, R.drawable.apple_image, category = "Bakery"),
            Product(14, "Muffin", "$2.25", "/piece", 4.3f, R.drawable.apple_image, category = "Bakery"),
            Product(15, "Donut", "$1.50", "/piece", 4.0f, R.drawable.apple_image, category = "Bakery"),

            // Milk Products (5 items)
            Product(16, "Whole Milk", "$3.20", "/liter", 4.4f, R.drawable.apple_image, category = "Dairy"),
            Product(17, "Greek Yogurt", "$4.50", "/500g", 4.7f, R.drawable.apple_image, category = "Dairy"),
            Product(18, "Cheddar Cheese", "$6.75", "/KG", 4.5f, R.drawable.apple_image, category = "Dairy"),
            Product(19, "Butter", "$5.20", "/500g", 4.2f, R.drawable.apple_image, category = "Dairy"),
            Product(20, "Cream", "$2.90", "/250ml", 4.1f, R.drawable.apple_image, category = "Dairy")
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
    ) {
        // Green header bar - Fixed at top
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFF2E7D32))
        ) {
            Text(
                text = "Fresh Groceries",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Scrollable products grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 100.dp // Extra bottom padding for better scrolling experience
            ),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f) // This ensures the grid takes remaining space and is scrollable
        ) {
            items(products) { product ->
                ProductCard(
                    product = product,
                    onFavoriteClick = {
                        val index = products.indexOf(product)
                        if (index != -1) {
                            products[index] = product.copy(isFavorite = !product.isFavorite)
                        }
                    },
                    onAddClick = { /* Handle add to cart */ }
                )
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    onFavoriteClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image section with favorite button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        getCategoryColor(product.category),
                        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                // Product image placeholder
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center)
                        .background(
                            getCategoryDarkColor(product.category),
                            RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = getEmojiForProduct(product.name, product.category),
                        fontSize = 48.sp
                    )
                }

                // Favorite button
                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        imageVector = if (product.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (product.isFavorite) Color.Red else Color(0xFF4CAF50),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            // Product details section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Product name
                Text(
                    text = product.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E2E2E)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Star rating
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(5) { index ->
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Star",
                            tint = if (index < product.rating.toInt()) Color(0xFFFF9800) else Color(0xFFE0E0E0),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Price and add button row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = product.price,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4CAF50)
                            )
                            Text(
                                text = product.unit,
                                fontSize = 12.sp,
                                color = Color(0xFF757575),
                                modifier = Modifier.padding(start = 2.dp)
                            )
                        }
                    }

                    // Add button
                    FloatingActionButton(
                        onClick = onAddClick,
                        modifier = Modifier.size(36.dp),
                        containerColor = Color(0xFF4CAF50),
                        contentColor = Color.White
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Add to cart",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun getCategoryColor(category: String): Color {
    return when (category) {
        "Fruit" -> Color(0xFFE8F5E8)      // Light green
        "Vegetable" -> Color(0xFFE8F8E8)  // Very light green
        "Bakery" -> Color(0xFFFFF8E1)     // Light yellow
        "Dairy" -> Color(0xFFE3F2FD)      // Light blue
        else -> Color(0xFFF5F5F5)         // Light gray
    }
}

@Composable
fun getCategoryDarkColor(category: String): Color {
    return when (category) {
        "Fruit" -> Color(0xFFDCEDC8)      // Darker green
        "Vegetable" -> Color(0xFFC8E6C9)  // Medium green
        "Bakery" -> Color(0xFFFFF176)     // Medium yellow
        "Dairy" -> Color(0xFFBBDEFB)      // Medium blue
        else -> Color(0xFFE0E0E0)         // Medium gray
    }
}

@Composable
fun getEmojiForProduct(productName: String, category: String): String {
    return when (productName.lowercase()) {
        // Fruits
        "orange" -> "🍊"
        "banana" -> "🍌"
        "strawberry" -> "🍓"
        "apple" -> "🍎"
        "mango" -> "🥭"

        // Vegetables
        "carrot" -> "🥕"
        "tomato" -> "🍅"
        "broccoli" -> "🥦"
        "bell pepper" -> "🫑"
        "spinach" -> "🥬"

        // Bakery
        "white bread", "bread" -> "🍞"
        "croissant" -> "🥐"
        "bagel" -> "🥯"
        "muffin" -> "🧁"
        "donut" -> "🍩"

        // Dairy
        "whole milk", "milk" -> "🥛"
        "greek yogurt", "yogurt" -> "🍶"
        "cheddar cheese", "cheese" -> "🧀"
        "butter" -> "🧈"
        "cream" -> "🥛"

        else -> when (category) {
            "Fruit" -> "🍎"
            "Vegetable" -> "🥕"
            "Bakery" -> "🍞"
            "Dairy" -> "🥛"
            else -> "🛒"
        }
    }
}
