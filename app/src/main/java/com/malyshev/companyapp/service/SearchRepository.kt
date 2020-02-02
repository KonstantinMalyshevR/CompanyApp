package com.malyshev.companyapp.service

import com.malyshev.companyapp.data.Company
import io.reactivex.Observable

class CompaniesRepository(val apiService: ApiService) {
    fun getCompany(id: String): Observable<List<Company>> {
        return apiService.getCompany(query = id)
    }

    fun getAllCompanies(): Observable<List<Company>> {
        return apiService.getAllCompanies()
    }
}