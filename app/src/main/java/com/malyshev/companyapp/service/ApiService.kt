package com.malyshev.companyapp.service

import com.malyshev.companyapp.data.Company
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("test_task/test.php")
    fun getAllCompanies(): Observable<List<Company>>

    @GET("test_task/test.php")
    fun getCompany(@Query("id") query: String): Observable<List<Company>>

    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://megakohz.bget.ru/")
                .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}