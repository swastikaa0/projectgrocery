package com.example.c36a.view

fun main() {
    //in java
//    System.Out.println("")
    //in kotlin
//    println("Hello world")

    //mutable variable
//    var name = "sandis"
//
//    name = "ram"

    //immutable variable

//    val age = 10
//
//    age = 15

    //in java
//    String name = "sandis"
    //in kotlin
//    var name: String = "sandis"
//    var age: Int = 18
//
//    println("My name is" + " " + name + "and I am " + age + " years old ")
//
//    print("My name is ${name.lowercase()} and I am $age years old")
//
////    var address = arrayOf("ktm","bhaktapur","lalitpur")
////
////    address[3] = "banepa"
//
//    var address = arrayListOf("ktm","pokhara")
//    var country = ArrayList<Any>()
//
//    println(address[0])
//    country.add("nepal")
//    country.add(2)
//
//    address.add("lalitpur")
//
//    address.removeAt(0)
//
//    var meanings = mapOf(
//        "Apple" to "This is fruits",
//        "Samsung" to "This is phone",
//        "Waiwai" to "This is noodles",
//        "Rose" to "This is flower",
//    )
//
//    println(meanings["Apple"])


    println("Enter days of week: ")
    var days = readln().toInt()
    var weekDay: String

    when (days) {
        1 -> weekDay = "Sunday"
        2 -> weekDay = "Monday"
        3 -> weekDay = "Tuesday"
        else -> weekDay = "Invalid input"
    }

}

//void calculate(int a ,int b){
//
//}

//fun calculate(a : Int,b: Int) : Unit {
//
//}