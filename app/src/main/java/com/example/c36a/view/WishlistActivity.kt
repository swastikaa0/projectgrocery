package com.example.c36a.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.c36a.model.CartItemModel
import com.example.c36a.model.WishlistItemModel
import com.example.c36a.repository.CartRepositoryImpl
import com.example.c36a.repository.WishlistRepositoryImpl
import com.example.c36a.viewmodel.CartViewModel
import com.example.c36a.viewmodel.CartViewModelFactory
import com.example.c36a.viewmodel.WishlistViewModel
import com.example.c36a.viewmodel.WishlistViewModelFactory

class WishlistActivity : ComponentActivity() {
    private lateinit var wishlistViewModel: WishlistViewModel
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize WishlistViewModel
        wishlistViewModel = ViewModelProvider(
            this,
            WishlistViewModelFactory(WishlistRepositoryImpl)
        )[WishlistViewModel::class.java]

        // Initialize CartViewModel
        cartViewModel = ViewModelProvider(
            this,
            CartViewModelFactory(CartRepositoryImpl())
        )[CartViewModel::class.java]

        setContent {
            WishlistScreen(wishlistViewModel, cartViewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistScreen(
    wishlistViewModel: WishlistViewModel,
    cartViewModel: CartViewModel
) {
    val context = LocalContext.current
    val wishlistItems by wishlistViewModel.wishlistItems.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Your Wishlist", fontSize = 20.sp) },
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, DashboardUserActivity::class.java)
                        context.startActivity(intent)
                        // Optional: finish the current activity
                        if (context is WishlistActivity) {
                            context.finish()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFF4A261))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFFF0F0F0))
        ) {
            if (wishlistItems.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Your wishlist is empty.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    items(wishlistItems) { item ->
                        WishlistItemCard(
                            item = item,
                            onRemove = {
                                wishlistViewModel.removeFromWishlist(item)
                                Toast.makeText(context, "Removed from wishlist", Toast.LENGTH_SHORT).show()
                            },
                            onAddToCart = {
                                val cartItem = item.toCartItem()
                                cartViewModel.addToCart(cartItem)
                                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WishlistItemCard(
    item: WishlistItemModel,
    onRemove: () -> Unit,
    onAddToCart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F2F1))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.productName, fontSize = 18.sp, color = Color.Black)
            Text(text = "Price: Rs. ${item.productPrice}", color = Color.DarkGray)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(onClick = onAddToCart) {
                    Text("Add to Cart")
                }
                Button(onClick = onRemove, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                    Text("Remove")
                }
            }
        }
    }
}


fun WishlistItemModel.toCartItem(): CartItemModel {
    return CartItemModel(
        id = "", // You can generate ID later or leave blank for auto generation
        productName = this.productName,
        productPrice = this.productPrice,
        image = this.image,
        quantity = 1
    )
}