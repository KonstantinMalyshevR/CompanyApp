package com.malyshev.companyapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.malyshev.companyapp.R
import com.malyshev.companyapp.adapters.CompaniesAdapter
import com.malyshev.companyapp.service.CompaniesRepositoryProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.malyshev.companyapp.databinding.ActivityMainBinding
import com.malyshev.companyapp.service.CompaniesRepository

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var companiesRepository: CompaniesRepository

    private lateinit var adapter: CompaniesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        companiesRepository = CompaniesRepositoryProvider.provideCompaniesRepository()

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CompaniesAdapter(this)
        binding.recyclerView.adapter = adapter

        adapter.onItemClick= {
            val intent = Intent(this, DetailsActivity::class.java);
            intent.putExtra("id", it.id)
            startActivity(intent)
        }

        loadObjects()
    }

    private fun loadObjects() {
        binding.progressBar.visibility = View.VISIBLE

        companiesRepository.getAllCompanies()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({ result ->
                binding.progressBar.visibility = View.GONE
                adapter.setItems(result)
            }, { error ->
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_LONG)
                error.printStackTrace()
            })
    }

}