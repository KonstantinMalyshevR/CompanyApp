package com.malyshev.companyapp.service

object CompaniesRepositoryProvider {
    val apiService = ApiService.create()

    fun provideCompaniesRepository(): CompaniesRepository {
        return CompaniesRepository(apiService)
    }
}