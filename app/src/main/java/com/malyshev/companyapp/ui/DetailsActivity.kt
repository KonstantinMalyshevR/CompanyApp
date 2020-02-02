package com.malyshev.companyapp.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.malyshev.companyapp.R
import com.malyshev.companyapp.data.Company
import com.malyshev.companyapp.databinding.ActivityDetailsBinding
import com.malyshev.companyapp.service.CompaniesRepository
import com.malyshev.companyapp.service.CompaniesRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class DetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailsBinding
    lateinit var companiesRepository: CompaniesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        companiesRepository = CompaniesRepositoryProvider.provideCompaniesRepository()

        val ss:String = intent.getStringExtra("id")

        loadObject(ss)
    }

    private fun loadObject(id: String) {
        binding.progressBar.visibility = View.VISIBLE

        companiesRepository.getCompany(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({ result ->
                binding.progressBar.visibility = View.GONE
                setView(result.get(0))
            }, { error ->
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_LONG)
                error.printStackTrace()
            })
    }

    private fun setView(company: Company) {
        Glide.with(this)
            .load("http://megakohz.bget.ru/test_task/${company.img}")
            .into(binding.imageView)
            .clearOnDetach()

        binding.tvName.text = "Название: ${company.name}"
        binding.tvDesc.text = "О компании: ${company.description}"
        binding.tvPhone.text = "Телефон: ${company.phone}"
        binding.tvSite.text = "Сайт: ${company.www}"
        binding.tvPlace.text = "LatLng: ${company.lat} - ${company.lon}"
    }
}
