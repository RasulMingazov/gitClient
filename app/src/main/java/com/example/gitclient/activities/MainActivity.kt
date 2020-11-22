package com.example.gitclient.activities
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitclient.R
import com.example.gitclient.api.GithubApi
import com.example.gitclient.model.pojo.NewRepo
import com.example.gitclient.model.pojo.Repo
import com.example.gitclient.repository.Repository
import com.example.gitclient.viewmodel.MainViewModel
import com.example.gitclient.viewmodel.factory.MainViewModelFactory
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSignInActivity(view: View) {
        val secondActivity = Intent(this, webActivity::class.java)
        startActivity(secondActivity)
    }

}