package com.example.c36a.viewmodel

//import OrderViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.c36a.repository.OrderRepository

class OrderViewModelFactory(private val orderRepository: OrderRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrderViewModel(orderRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}