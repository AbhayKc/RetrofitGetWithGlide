package com.example.retrofitget

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.retrofitget.databinding.ActivityMainBinding
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var adapterRV:Adapter
    private lateinit var recyclerView: RecyclerView
    private var arrayList: ArrayList<DataX?>? = ArrayList()

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        recyclerView = binding.recycler
        adapterRV = Adapter(arrayList ?: ArrayList(), this@MainActivity)

        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.adapter = adapterRV

        getData()
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor())
        .build()
    private fun getData() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://anc-events.apa1906.app/public/api/")
            .client(client)
            .build()

        val apiService = retrofit.create(Interface::class.java)
        apiService.getData().enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                val body = response.body()
                if (body != null) {
                    adapterRV.setArraylist(body.data)
                    for (item in body.data!!) {
                        if (item != null) {
                            Log.d("API Response", "Name: ${item.name}, ID: ${item.id}, Icon: ${item.icon}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.d("checkResponse", t.toString())
            }
        })
    }
    internal fun glideImageSet(context: Context, image: String?, img: ImageView) {
        Glide.with(context).load(image).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true).priority(Priority.IMMEDIATE)
          .into(img)
    }

}