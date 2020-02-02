package com.malyshev.companyapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.malyshev.companyapp.R
import com.malyshev.companyapp.data.Company
import kotlinx.android.synthetic.main.company_list_item.view.*

class CompaniesAdapter(val context: Context) : RecyclerView.Adapter<CompaniesAdapter.ViewHolder>() {

    var onItemClick: ((Company) -> Unit)? = null
    private var items : List<Company> = ArrayList()

    fun setItems(items : List<Company>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.company_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load("http://megakohz.bget.ru/test_task/${items.get(position).img}")
            .into(holder.imageView)

        holder.textView?.text = items.get(position).name
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val imageView = view.imageView
        val textView = view.textView

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }
    }
}

