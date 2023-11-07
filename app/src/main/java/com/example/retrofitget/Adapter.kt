package com.example.retrofitget

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitget.databinding.ItemBinding
import kotlinx.coroutines.withContext


class Adapter(private var arrayList: ArrayList<DataX?>? = ArrayList(),private val activity: Activity) : RecyclerView.Adapter<Adapter.UserDataViewHolder>() {

    class UserDataViewHolder(binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val t2 = binding.tv2
        val t1 = binding.tv1
        val img = binding.image
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserDataViewHolder(binding)
    }
    override fun onBindViewHolder(holder: UserDataViewHolder, position: Int) {
        arrayList?.get(position)?.apply {
        holder.apply {
            t1.text = name
            t2.text = id.toString()
            //Glide.with(img.context).load(data.icon).into(img)
            val url = "https://anc-events.apa1906.app/public/uploads/social/$icon"
            (activity as MainActivity).glideImageSet(holder.itemView.context, url, img)
        }
        }
    }

    override fun getItemCount(): Int {
        return arrayList?.size!!
    }
    @SuppressLint("NotifyDataSetChanged")
    internal fun setArraylist(list:ArrayList<DataX?>?){

        if(arrayList?.size !!  >0){
            arrayList?.clear()
        }
        if (list != null) {
            arrayList?.addAll(list)
        }
        notifyDataSetChanged()
    }
}
