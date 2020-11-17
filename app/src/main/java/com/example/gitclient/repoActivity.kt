package com.example.gitclient

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitclient.adapter.RepoAdapter
import com.example.gitclient.model.pojo.Repo
import com.example.gitclient.viewmodel.MainViewModel
import com.example.gitclient.viewmodel.factory.MainViewModelFactory
import com.example.gitclient.repository.Repository
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class repoActivity: AppCompatActivity() {

    companion object {
        const val code = "code"
    }

    var tokenOfAccess = ""

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repos)

        tokenOfAccess = intent.getStringExtra(code).toString()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRepos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val repository = Repository()
        val viewModelFactory =
            MainViewModelFactory(
                repository
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllUserRepos(tokenOfAccess)
        viewModel.myResponse.observe(this, Observer { responce ->
            recyclerView.adapter = RepoAdapter(responce)

        })
        
    }
}