package com.example.c36a.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.lifecycle.ViewModelProvider

import com.example.c36a.R
import com.example.c36a.repository.CartRepositoryImpl
import com.example.c36a.viewmodel.CartViewModel
import com.example.c36a.viewmodel.CartViewModelFactory
import com.example.c36a.viewmodel.OrderViewModel
import com.example.c36a.viewmodel.UserViewModel
import com.example.c36a.viewmodel.WishlistViewModel
import com.example.c36a.viewmodel.UserViewModelFactory
import com.example.c36a.viewmodel.OrderViewModelFactory
import com.example.c36a.repository.OrderRepositoryImpl
import com.example.c36a.repository.UserRepositoryImpl
import com.example.c36a.viewmodel.WishlistViewModelFactory




//class Home : ComponentActivity(){
//
//    private lateinit var cartViewModel: CartViewModel
//    private lateinit var wishlistViewModel: WishlistViewModel
//    private lateinit var userViewModel: UserViewModel
//    private lateinit var orderViewModel: OrderViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        cartViewModel = ViewModelProvider(this, CartViewModelFactory(CartRepositoryImpl()))[CartViewModel::class.java]
//        wishlistViewModel = ViewModelProvider(this, WishlistViewModelFactory(WishlistRepositoryImpl))[WishlistViewModel::class.java]
//        userViewModel = ViewModelProvider(this, UserViewModelFactory(UserRepositoryImpl()))[UserViewModel::class.java]
//        orderViewModel = ViewModelProvider(this, OrderViewModelFactory(OrderRepositoryImpl()))[OrderViewModel::class.java]
//
//        setContent {
//            UserDashboardBody(cartViewModel, wishlistViewModel, userViewModel, orderViewModel)
//        }
//    }
//}


@Composable
fun Home(){
    val primaryGreen = Color(0xFF2F7D00)
    val lightBackground = Color(0xFFFDFDFD)

    val categories = listOf(
        "Fruits" to R.drawable.fruits_image,
        "Vegetables" to R.drawable.vegetables_image,
        "Dairy" to R.drawable.milk_image,
        "Snacks" to R.drawable.snacks_image
    )

    val popularItems = listOf(
        "Apple - $1.99/kg" to R.drawable.apple_image,
        "Tomato - $0.99/kg" to R.drawable.tomato_image,
        "Milk - $1.50/ltr" to R.drawable.milk_image,
        "Biscuits - $2.49" to R.drawable.bakery_image
    )




        Column(
            modifier = Modifier
                .fillMaxSize()

                .padding(16.dp)
        ) {
            Text(
                text = "Hello, Shopper! 👋",
                fontSize = 24.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "What would you like to buy today?",
                fontSize = 16.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text("Popular Categories", fontSize = 18.sp, color = primaryGreen)

            Spacer(modifier = Modifier.height(12.dp))

//
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.height(340.dp) // adjust as needed
            ) {
                items(categories) { (title, imageRes) ->
                    CategoryCard(title = title, imageRes = imageRes)
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text("Popular Items", fontSize = 18.sp, color = primaryGreen)

            Spacer(modifier = Modifier.height(12.dp))

//
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.height(360.dp) // Adjust height based on your card size & rows
            ) {
                items(popularItems) { (title, imageRes) ->
                    PopularItemCard(title = title, imageRes = imageRes)
                }
            }

        }
    }




@Composable
fun CategoryCard(title: String, imageRes: Int) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(140.dp)
            .height(160.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontSize = 16.sp, color = Color.Black)
        }
    }
}

@Composable
fun PopularItemCard(
    title: String,
    imageRes: Int,
    onCardClick: () -> Unit = {},
    onAddToCartClick: () -> Unit = {}
) {
    var isAdded by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .width(180.dp)
            .height(160.dp)
            .clickable { onCardClick() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .padding(end = 12.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                Text(
                    text = title,
                    fontSize = 14.sp,
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
            }

            // Animate color change when button state changes
            val buttonColor by animateColorAsState(
                targetValue = if (isAdded) Color(0xFF4CAF50) else Color(0xFF2F7D00),
                animationSpec = tween(durationMillis = 500)
            )

            Button(
                onClick = {
                    isAdded = !isAdded
                    onAddToCartClick()
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(36.dp)
            ) {
                Text(
                    text = if (isAdded) "Added" else "Add to Cart",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}











