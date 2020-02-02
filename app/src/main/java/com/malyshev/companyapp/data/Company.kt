package com.malyshev.companyapp.data


data class Company(
    val id: String,
    val name: String,
    val img: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val www: String,
    val phone: String
)

data class Result (
//    val total_count: Int,
//    val incomplete_results: Boolean,
    val items: List<Company>
)