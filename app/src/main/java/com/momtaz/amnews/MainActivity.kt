package com.momtaz.amnews

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.momtaz.amnews.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadNews()
    }
    private fun loadNews(){
        val retrofit = Retrofit
            .Builder()
            .baseUrl("https://newsapi.org")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val newsCallable = retrofit.create(NewsCallable::class.java)
        newsCallable.getNews().enqueue(object : Callback<News>{
            override fun onResponse(p0: Call<News>, p1: Response<News>) {
                val news = p1.body()
                val articles =news?.articles
                Log.d("ok","articles $articles")
            }

            override fun onFailure(p0: Call<News>, p1: Throwable) {
                Log.d("ok","error ${p1.message}")

            }

        })
    }
}