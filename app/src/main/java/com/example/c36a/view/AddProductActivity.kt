package com.example.c36a.view

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.c36a.model.ProductModel
import com.example.c36a.repository.ProductRepositoryImpl
import com.example.c36a.utils.ImageUtils
import com.example.c36a.view.ui.theme.C36ATheme
import com.example.c36a.viewmodel.ProductViewModel
import com.example.c36a.R

//class AddProductActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            AddProductBody()
//        }
//    }
//}
//
//@Composable
//fun AddProductBody() {
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
//                        val model = ProductModel("",pName,pPrice.toDouble(),pDesc)
//                        viewModel.addProduct(model) { success, msg ->
//                            if (success) {
//                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
//                                activity?.finish()
//                            } else {
//                                Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
//
//                            }
//                        }
//
//                    },
//
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text("Add Product")
//                }
//            }
//        }
//
//    }
//}
//
//@Preview
//@Composable
//fun previewAddProductBody() {
//    AddProductBody()
//}
class AddProductActivity : ComponentActivity() {
    lateinit var imageUtils: ImageUtils
    var selectedImageUri by mutableStateOf<Uri?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        imageUtils = ImageUtils(this, this)
        imageUtils.registerLaunchers { uri ->
            selectedImageUri = uri
        }
        setContent {
            AddProductBody(
                selectedImageUri = selectedImageUri,
                onPickImage = { imageUtils.launchImagePicker() }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductBody(
    selectedImageUri: Uri?,
    onPickImage: () -> Unit
) {
    var pName by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }
    var pCategory by remember { mutableStateOf("Cricket") }

    val categories = listOf("Fruits", "Vegetable", "Bakery", "Dairy", "Snacks")

    val repo = remember { ProductRepositoryImpl() }
    val viewModel = remember { ProductViewModel(repo) }

    val context = LocalContext.current
    val activity = context as? Activity

    var categoryExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add New Product", fontWeight = FontWeight.Bold) },
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
                .padding(10.dp)
                .background(color = Color(0xFFFCCB8C)) // light green
        ) {
            item {

                Text(
                    text = "Product Information",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 10.dp)
                )

                // Image Picker

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                onPickImage()
                            }
                            .padding(10.dp)
                    ) {
                        if (selectedImageUri != null) {
                            AsyncImage(
                                model = selectedImageUri,
                                contentDescription = "Selected Image",
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
                                painterResource(R.drawable.placeholder),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    // Product Name
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        placeholder = { Text("Product Name") },
                        value = pName,
                        onValueChange = { pName = it }
                    )

                    // Product Description
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        placeholder = { Text("Product Description") },
                        value = pDesc,
                        onValueChange = { pDesc = it }
                    )

                    // Product Price
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp)
                            .padding(bottom = 16.dp),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text("Product Price") },
                        value = pPrice,
                        onValueChange = { pPrice = it }
                    )

                    // Category Dropdown
                    ExposedDropdownMenuBox(
                        expanded = categoryExpanded,
                        onExpandedChange = { categoryExpanded = !categoryExpanded },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        OutlinedTextField(
                            value = pCategory,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Category") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                            modifier = Modifier.menuAnchor()
                        )
                        ExposedDropdownMenu(
                            expanded = categoryExpanded,
                            onDismissRequest = { categoryExpanded = false }
                        ) {
                            categories.forEach { cat ->
                                DropdownMenuItem(
                                    text = { Text(cat) },
                                    onClick = {
                                        pCategory = cat
                                        categoryExpanded = false
                                    }
                                )
                            }
                        }
                    }

                    // Submit Button
                    Button(
                        onClick = {
                            if (selectedImageUri != null) {
                                viewModel.uploadImage(context, selectedImageUri) { imageUrl ->
                                    if (imageUrl != null) {
                                        val model = ProductModel(
                                            "",
                                            pName,
                                            pPrice.toDoubleOrNull() ?: 0.0,
                                            pDesc,
                                            imageUrl,
                                            pCategory
                                        )
                                        viewModel.addProduct(model) { success, message ->
                                            Toast.makeText(context, message, Toast.LENGTH_LONG)
                                                .show()
                                            if (success) activity?.finish()
                                        }
                                    } else {
                                        Log.e(
                                            "Upload Error",
                                            "Failed to upload image to Cloudinary"
                                        )
                                    }
                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please select an image first",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text("Submit")
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewAddProductBody() {
        AddProductBody(
            selectedImageUri = null,
            onPickImage = {}
        )
    }
