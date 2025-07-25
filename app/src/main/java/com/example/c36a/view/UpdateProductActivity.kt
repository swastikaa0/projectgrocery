package com.example.c36a.view

//import android.app.Activity
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.OutlinedTextFieldDefaults
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.material3.TopAppBarDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.setValue
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Color.Companion.Green
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.c36a.model.ProductModel
//import com.example.c36a.repository.ProductRepositoryImpl
//import com.example.c36a.view.ui.theme.C36ATheme
//import com.example.c36a.viewmodel.ProductViewModel
//import kotlin.toString
//
//class UpdateProductActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            UpdateProductBody()
//        }
//    }
//}
//
//@Composable
//fun UpdateProductBody() {
//    val repo = remember { ProductRepositoryImpl() }
//    val viewModel = remember { ProductViewModel(repo) }
//    val context = LocalContext.current
//    val activity = context as? Activity
//
//    val productId: String? = activity?.intent?.getStringExtra("productId")
//
//    // Observe product LiveData as Compose state
//    val product by viewModel.product.observeAsState()
//
//    // Local editable states for form fields
//    var pName by remember { mutableStateOf("") }
//    var pPrice by remember { mutableStateOf("") }
//    var pDesc by remember { mutableStateOf("") }
//
//    // Load product details once when productId is available
//    LaunchedEffect(productId) {
//        productId?.let {
//            viewModel.getProductById(it)
//        }
//    }
//
//    // Update local states when product is loaded
//    LaunchedEffect(product) {
//        product?.let {
//            pName = it.productName ?: ""
//            pPrice = it.productPrice?.toString() ?: ""
//            pDesc = it.productDescription ?: ""
//        }
//    }
//
//    Scaffold{ innerPadding ->
//
//
//        LazyColumn(
//
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .background(color =Color(0xFFFCCB8C) )
//        )
//        {
//
//            item {
//                OutlinedTextField(
//                    value = pName,
//                    onValueChange = { pName = it },
//                    placeholder = { Text("Update product name") },
//                    modifier = Modifier.fillMaxWidth()
//                        .padding(bottom = 16.dp)
//
//
//                )
//                Spacer(modifier = Modifier.height(12.dp))
//                OutlinedTextField(
//                    value = pPrice,
//                    onValueChange = { pPrice = it },
//                    placeholder = { Text("Update price") },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(12.dp))
//                OutlinedTextField(
//                    value = pDesc,
//                    onValueChange = { pDesc = it },
//                    placeholder = { Text("Update Description") },
//                    minLines = 3,
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(12.dp))
//                Button(
//                    onClick = {
//                        val priceDouble = pPrice.toDoubleOrNull() ?: 0.0
//                        if (productId == null) {
//                            Toast.makeText(context, "Invalid product ID", Toast.LENGTH_LONG).show()
//                            return@Button
//                        }
//                        val updateData = mutableMapOf<String, Any?>(
//                            "productName" to pName,
//                            "productPrice" to priceDouble,
//                            "productDescription" to pDesc
//                        )
//                        viewModel.updateProduct(productId, updateData) { success, msg ->
//                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
//                            if (success) {
//                                activity?.finish()
//                            }
//                        }
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text("Update Product")
//                }
//            }
//        }
//    }
//}


import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.c36a.model.ProductModel
import com.example.c36a.repository.ProductRepositoryImpl
import com.example.c36a.view.ui.theme.C36ATheme
import com.example.c36a.viewmodel.ProductViewModel
import kotlin.toString

class UpdateProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UpdateProductBody()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProductBody() {
    val repo = remember { ProductRepositoryImpl() }
    val viewModel = remember { ProductViewModel(repo) }
    val context = LocalContext.current
    val activity = context as? Activity

    val productId: String? = activity?.intent?.getStringExtra("productId")

    // Observe product LiveData as Compose state
    val product by viewModel.product.observeAsState()

    // Local editable states for form fields
    var pName by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }

    // Load product details once when productId is available
    LaunchedEffect(productId) {
        productId?.let {
            viewModel.getProductById(it)
        }
    }

    // Update local states when product is loaded
    LaunchedEffect(product) {
        product?.let {
            pName = it.productName ?: ""
            pPrice = it.productPrice?.toString() ?: ""
            pDesc = it.productDescription ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Update Product",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF4A261),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color(0xFFFCCB8C))
                .padding(16.dp)
        ) {
            item {
                // Header Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Product",
                        modifier = Modifier
                            .size(50.dp)
                            .padding(bottom = 12.dp),
                        tint = Color(0xFF4CAF50)
                    )
                    Text(
                        text = "Update Product Details",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333)
                    )
                }

                // Product Name Section
                Text(
                    text = "Product Name",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = pName,
                    onValueChange = { pName = it },
                    placeholder = { Text("Enter product name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF4CAF50),
                        unfocusedBorderColor = Color(0xFFBDBDBD)
                    )
                )

                // Product Price Section
                Text(
                    text = "Product Price (₹)",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = pPrice,
                    onValueChange = { pPrice = it },
                    placeholder = { Text("Enter price") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF4CAF50),
                        unfocusedBorderColor = Color(0xFFBDBDBD)
                    )
                )

                // Product Description Section
                Text(
                    text = "Product Description",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                OutlinedTextField(
                    value = pDesc,
                    onValueChange = { pDesc = it },
                    placeholder = { Text("Enter product description") },
                    minLines = 4,
                    maxLines = 6,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF4CAF50),
                        unfocusedBorderColor = Color(0xFFBDBDBD)
                    )
                )

                // Update Button
                Button(
                    onClick = {
                        val priceDouble = pPrice.toDoubleOrNull() ?: 0.0
                        if (productId == null) {
                            Toast.makeText(context, "Invalid product ID", Toast.LENGTH_LONG).show()
                            return@Button
                        }
                        if (pName.isEmpty() || pPrice.isEmpty()) {
                            Toast.makeText(context, "Please fill all required fields", Toast.LENGTH_LONG).show()
                            return@Button
                        }
                        val updateData = mutableMapOf<String, Any?>(
                            "productName" to pName,
                            "productPrice" to priceDouble,
                            "productDescription" to pDesc
                        )
                        viewModel.updateProduct(productId, updateData) { success, msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                            if (success) {
                                activity?.finish()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFF4A261)
                    )
                ) {
                    Text(
                        "Update Product",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }

                // Spacing at the bottom
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
//@Composable
//fun UpdateProductBody() {
//    var pName by remember { mutableStateOf("") }
//    var pPrice by remember { mutableStateOf("") }
//    var pDesc by remember { mutableStateOf("") }
//
//    val repo = remember { ProductRepositoryImpl() }
//    val viewModel = remember { ProductViewModel(repo) }
//
//    val context = LocalContext.current
//    val activity = context as? Activity
//
//
//    val productId : String? = activity?.intent?.getStringExtra("productId")
//
//    val products = viewModel.products.observeAsState(initial = null)
//
//    LaunchedEffect(Unit) {
//        viewModel.getProductById(productId.toString())
//    }
//
//    pName = products.value?.productName ?: ""
//    pDesc = products.value?.productDesc ?: ""
//    pPrice = products.value?.productPrice.toString()
//
//
//
//    Scaffold { innerPadding ->
//        LazyColumn(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//        ) {
//            item {
//                OutlinedTextField(
//                    value = pName,
//                    onValueChange = {
//                        pName = it
//                    },
//                    placeholder = {
//                        Text("Enter product name")
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                )
//                Spacer(modifier = Modifier.height(12.dp))
//                OutlinedTextField(
//                    value = pPrice,
//                    onValueChange = {
//                        pPrice = it
//                    },
//                    placeholder = {
//                        Text("Enter price")
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//                OutlinedTextField(
//                    value = pDesc,
//                    onValueChange = {
//                        pDesc = it
//                    },
//                    placeholder = {
//                        Text("Enter Description")
//                    },
//                    minLines = 3,
//                    modifier = Modifier.fillMaxWidth()
//                )
//
//                Spacer(modifier = Modifier.height(12.dp))
//                Button(
//                    onClick = {
//
//                        var data = mutableMapOf<String,Any?>()
//
//                        data["productDesc"] = pDesc
//                        data["productPrice"] = pPrice.toDouble()
//                        data["productName"] = pName
//                        data["productId"] = productId
//
//                        viewModel.updateProduct(
//                            productId.toString(),data
//                        ) {
//                                success,message->
//                            if(success){
//                                activity?.finish()
//                            }else{
//                                Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
//                            }
//                        }
//
//
////                        val model = ProductModel("",pName,pPrice.toDouble(),pDesc)
////                        viewModel.addProduct(model) { success, msg ->
////                            if (success) {
////                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
////                                activity?.finish()
////                            } else {
////                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
////
////                            }
////                        }
//
//                    },
//
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text("Update Product")
//                }
//            }
//        }
//
//    }
//}
//
//@Preview
//@Composable
//fun previewUpdateProductBody() {
//    AddProductBody()
//}