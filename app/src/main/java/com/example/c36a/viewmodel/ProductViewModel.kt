package com.example.c36a.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.c36a.model.ProductModel
import com.example.c36a.repository.ProductRepository

class ProductViewModel(val repo : ProductRepository): ViewModel() {
    fun addProduct(product: ProductModel,
                   callback: (Boolean, String) -> Unit){
        repo.addProduct(product,callback)

    }

    fun updateProduct(productId: String, data: MutableMap<String,Any?>,
                      callback: (Boolean, String) -> Unit){
        repo.updateProduct(productId,data,callback)
    }

    fun deleteProduct(productId: String,
                      callback: (Boolean, String) -> Unit) {
        repo.deleteProduct (productId, callback )

    }

    private val _products = MutableLiveData<ProductModel?>()
    val products : LiveData<ProductModel?> get() = _products

    private val _allProducts = MutableLiveData<List<ProductModel?>>()
    val allProducts: LiveData<List<ProductModel?>> get() = _allProducts

    fun getProductById(productId: String,
    ){
        repo.getProductById (productId){  data,  success,msg ->
            if (success){
                _products.postValue(data)}
            else {
                _products.postValue(null)
            }

        }

    }
    private var _loading = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
        get() = _loading


    fun getAllProducts(){
        _loading.postValue(true)
        repo.getAllProducts { data,success, msg,  ->

//        repo.getAllProducts  { data,  success,msg ->
            if (success) {
                _loading.postValue(false)
                _allProducts.postValue(data)
            } else {
                _loading.postValue(false)
                _allProducts.postValue(emptyList())

            }
        }
    }

    fun searchProducts(query: String,
                       callback: (List<ProductModel>?, Boolean, String) -> Unit){}

    fun getProductsByCategory(category: String,
                              callback: (List<ProductModel>?, Boolean, String) -> Unit){}

    fun getProductsByPriceRange(minPrice: Int, maxPrice: Int,
                                callback: (List<ProductModel>?, Boolean, String) -> Unit){}

    fun getProductsByAvailability(isAvailable: Boolean,
                                  callback: (List<ProductModel>?, Boolean, String) -> Unit){}

    fun getProductsByRating(rating: Double,
                            callback: (List<ProductModel>?, Boolean, String) -> Unit){}

    fun getProductsByDiscount(discount: Double,
                              callback: (List<ProductModel>?, Boolean, String) -> Unit){}

}
