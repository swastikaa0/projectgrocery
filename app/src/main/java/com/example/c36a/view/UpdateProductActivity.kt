package com.example.c36a.view

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.c36a.model.ProductModel
import com.example.c36a.repository.ProductRepositoryImpl
import com.example.c36a.view.ui.theme.C36ATheme
import com.example.c36a.viewmodel.ProductViewModel

class UpdateProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()
        setContent {
            UpdateProductActivity()
        }
    }
}

@Composable
fun UpdateProductBody() {
    var pName by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }

    val repo = remember { ProductRepositoryImpl() }
    val viewModel = remember { ProductViewModel(repo) }

    val context = LocalContext.current
    val activity = context as? Activity


    val productId : String? = activity?.intent?.getStringExtra("productId")

    val products = viewModel.products.observeAsState(initial = null)

    LaunchedEffect(Unit) {
        viewModel.getProductById(productId.toString())
    }

    pName = products.value?.productName ?: ""
    pDesc = products.value?.productDesc ?: ""
    pPrice = products.value?.productPrice.toString()



    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                OutlinedTextField(
                    value = pName,
                    onValueChange = {
                        pName = it
                    },
                    placeholder = {
                        Text("Enter product name")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = pPrice,
                    onValueChange = {
                        pPrice = it
                    },
                    placeholder = {
                        Text("Enter price")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = pDesc,
                    onValueChange = {
                        pDesc = it
                    },
                    placeholder = {
                        Text("Enter Description")
                    },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {

                        var data = mutableMapOf<String,Any?>()

                        data["productDesc"] = pDesc
                        data["productPrice"] = pPrice.toDouble()
                        data["productName"] = pName
                        data["productId"] = productId

                        viewModel.updateProduct(
                            productId.toString(),data
                        ) {
                                success,message->
                            if(success){
                                activity?.finish()
                            }else{
                                Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
                            }
                        }


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

                    },

                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Update Product")
                }
            }
        }

    }
}

@Preview
@Composable
fun previewUpdateProductBody() {
    AddProductBody()
}