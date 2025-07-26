package com.example.c36a.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.c36a.model.CartItemModel
import com.example.c36a.model.OrderModel
import com.example.c36a.repository.CartRepositoryImpl
import com.example.c36a.repository.OrderRepositoryImpl
import com.example.c36a.ui.theme.C36ATheme
import com.example.c36a.viewmodel.CartViewModel
import com.example.c36a.viewmodel.CartViewModelFactory
import com.example.c36a.viewmodel.OrderViewModel
import com.example.c36a.viewmodel.OrderViewModelFactory



class Cart : ComponentActivity (){

//    private lateinit var cartViewModel: CartViewModel
//    private lateinit var orderViewModel: OrderViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val cartRepo = CartRepositoryImpl()
        val orderRepo = OrderRepositoryImpl()

        val cartFactory = CartViewModelFactory(cartRepo)
        cartViewModel = ViewModelProvider(this,cartFactory)[CartViewModel::class.java]

        val orderFactory = OrderViewModelFactory(orderRepo)
        orderViewModel = ViewModelProvider(this, orderFactory)[OrderViewModel::class.java]

        cartViewModel.loadCartItems()

        setContent {
            C36ATheme {
                CartScreen(cartViewModel = cartViewModel, orderViewModel = orderViewModel)
            }
        }
    }
}









//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CartScreen(cartViewModel: CartViewModel, orderViewModel: OrderViewModel) {
//    val cartItems by cartViewModel.cartItems.observeAsState(emptyList())
//    val errorMessage by cartViewModel.error.observeAsState()
//    val orderError by orderViewModel.error.observeAsState()
//    val context = LocalContext.current
//
//
//    val totalPrice = cartItems.sumOf { it.productPrice * it.quantity }
//
//    LaunchedEffect(orderError) {
//        orderError?.let {
//            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//            orderViewModel.clearError()
//        }
//    }
////
//
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Your Cart", fontSize = 20.sp) },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF4CAF50)  // Green color
//                )
//            )
//        },
//        content = { padding ->
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(padding)
//                    .padding(16.dp)
//            ) {
//                if (!errorMessage.isNullOrEmpty()) {
//                    Text(text = errorMessage ?: "", color = MaterialTheme.colorScheme.error)
//                }
//
//                LazyColumn(
//                    modifier = Modifier.weight(1f)
//                ) {
//                   items(cartItems) { item ->
//                       CartItemCard(
//                           item = item,
//                           onIncrease = {
//                               cartViewModel.updateQuantity(item.id, item.quantity +1)
//                           }
//                       )
//                   }
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text("Total:", fontSize = 18.sp, style = MaterialTheme.typography.titleMedium)
//                    Text("Rs. ${"%.2f".format(totalPrice)}", fontSize = 20.sp, style = MaterialTheme.typography.titleLarge)
//                }
//
//                Button(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(top = 12.dp),
//                    onClick = {
//                        if (cartItems.isNotEmpty()) {
//                            val userId = "USR001"  // Replace with actual user ID from auth
//                            val order = OrderModel(
//                                orderId = "",  // will be set by Firebase on backend
//                                userId = userId,
//                                items = cartItems,
//                                totalAmount = totalPrice,
//                                orderStatus = "Pending"
//                            )
//                            orderViewModel.placeOrder(order)
//                            Toast.makeText(context, "Order placed!", Toast.LENGTH_SHORT).show()
//                            // Optionally clear cart after order placed
//                            // cartViewModel.clearCart()
//                        } else {
//                            Toast.makeText(context, "Cart is empty", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                ) {
//                    Text("Proceed to Checkout")
//                }
//            }
//
//
//        @Composable
//
//        fun CartItemCard(
//            item: CartItemModel,
//            onIncrease: () -> Unit,
//            onDecrease: () -> Unit,
//            onRemove: () -> Unit
//        ) {
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//            ) {
//                Row(
//                    modifier = Modifier.padding(16.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
////                    Image(
////                        painter = rememberAsyncImagePainter(item.image),
////                        contentDescription = item.productName,
////                        modifier = Modifier
////                            .size(80.dp)
////                            .padding(end = 16.dp),
////                        contentScale = ContentScale.Crop
////                    )
//
//                    Column(modifier = Modifier.weight(1f)) {
//                        Text(text = item.productName, fontSize = 16.sp, style = MaterialTheme.typography.titleMedium)
//                        Text(text = "Rs. ${item.productPrice}", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
//
//                        Row(
//                            verticalAlignment = Alignment.CenterVertically,
//                            modifier = Modifier.padding(top = 8.dp)
//                        ) {
//                            Button(onClick = onDecrease, contentPadding = PaddingValues(4.dp)) {
//                                Text("-")
//                            }
//                            Text(
//                                text = "${item.quantity}",
//                                modifier = Modifier.padding(horizontal = 8.dp),
//                                fontSize = 16.sp
//                            )
//                            Button(onClick = onIncrease, contentPadding = PaddingValues(4.dp)) {
//                                Text("+")
//                            }
//                        }
//                    }
//
//                    IconButton(onClick = onRemove) {
//                        Icon(Icons.Default.Delete, contentDescription = "Remove")
//                    }
//                }
//            }
//        }

//@Composable
//fun CartScreen(cartViewModel: CartViewModel, orderViewModel: OrderViewModel) {
//    val cartItems by cartViewModel.cartItems.observeAsState(emptyList())
//
//    val errorMessage by cartViewModel.error.observeAsState()
//    val orderError by orderViewModel.error.observeAsState()
//    val context = LocalContext.current
//
//    val totalPrice = cartItems.sumOf { it.productPrice * it.quantity }
//
//    LaunchedEffect(orderError) {
//        orderError?.let {
//            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//            orderViewModel.clearError()
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Your Cart", fontSize = 20.sp) },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF4CAF50)  // Green color
//                )
//            )
//        }
//    ) { padding ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//                .padding(16.dp)
//        ) {
//            if (!errorMessage.isNullOrEmpty()) {
//                Text(
//                    text = errorMessage ?: "",
//                    color = MaterialTheme.colorScheme.error
//                )
//            }
//
////            LazyColumn(
////                modifier = Modifier.weight(1f),
////                contentPadding = PaddingValues(vertical = 8.dp)
////            ) {
////                items(items = cartItems, key = { item -> item.id }) { item ->
////                    CartItemCard(
////                        item = item,
////                        onIncrease = { cartViewModel.updateQuantity(item.id, item.quantity + 1) },
////                        onDecrease = {
////                            if (item.quantity > 1) {
////                                cartViewModel.updateQuantity(item.id, item.quantity - 1)
////                            }
////                        },
////                        onRemove = { cartViewModel.removeItem(item.id) }
////                    )
////                }
////            }
//
//
//
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text("Total:", fontSize = 18.sp, style = MaterialTheme.typography.titleMedium)
//                Text("Rs. ${"%.2f".format(totalPrice)}", fontSize = 20.sp, style = MaterialTheme.typography.titleLarge)
//            }
//
//            Button(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 12.dp),
//                onClick = {
//                    if (cartItems.isNotEmpty()) {
//                        val userId = "USR001"  // Replace with actual user ID from auth
//                        val order = OrderModel(
//                            orderId = "",
//                            userId = userId,
//                            items = cartItems,
//                            totalAmount = totalPrice,
//                            orderStatus = "Pending"
//                        )
//                        orderViewModel.placeOrder(order)
//                        Toast.makeText(context, "Order placed!", Toast.LENGTH_SHORT).show()
//                        // Optionally clear cart
//                        // cartViewModel.clearCart()
//                    } else {
//                        Toast.makeText(context, "Cart is empty", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            ) {
//                Text("Proceed to Checkout")
//            }
//        }
//    }
//}
//
//@Composable
//fun CartItemCard(
//    item: CartItemModel,
//    onIncrease: () -> Unit,
//    onDecrease: () -> Unit,
//    onRemove: () -> Unit
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
////        Image(
////            painter = rememberAsyncImagePainter(item.image),
////            contentDescription = item.productName,
////            modifier = Modifier
////                .size(80.dp)
////                .padding(end = 16.dp),
////            contentScale = ContentScale.Crop
////        )
//
//            Column(modifier = Modifier.weight(1f)) {
//                Text(text = item.productName, fontSize = 16.sp, style = MaterialTheme.typography.titleMedium)
//                Text(text = "Rs. ${item.productPrice}", fontSize = 14.sp, color = MaterialTheme.colorScheme.primary)
//
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    modifier = Modifier.padding(top = 8.dp)
//                ) {
//                    Button(onClick = onDecrease, contentPadding = PaddingValues(4.dp)) {
//                        Text("-")
//                    }
//                    Text(
//                        text = "${item.quantity}",
//                        modifier = Modifier.padding(horizontal = 8.dp),
//                        fontSize = 16.sp
//                    )
//                    Button(onClick = onIncrease, contentPadding = PaddingValues(4.dp)) {
//                        Text("+")
//                    }
//                }
//            }
//
//            IconButton(onClick = onRemove) {
//                Icon(Icons.Default.Delete, contentDescription = "Remove")
//            }
//        }
//    }
//}







