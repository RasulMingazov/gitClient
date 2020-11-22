package com.example.gitclient.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitclient.R
import com.example.gitclient.adapter.RepoAdapter
import com.example.gitclient.viewmodel.MainViewModel
import com.example.gitclient.viewmodel.factory.MainViewModelFactory
import com.example.gitclient.repository.Repository


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
        Log.d("dsvvd",tokenOfAccess)
        createRecycleView()
    }

    fun createRecycleView() {  val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRepos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val repository = Repository()
        val viewModelFactory =
            MainViewModelFactory(
                repository
            )
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAllUserRepos(tokenOfAccess)
        viewModel.reposResponse.observe(this, Observer { responce ->

            if (responce.isSuccessful) {
                recyclerView.adapter = responce.body()?.let { RepoAdapter(it) }
            }
        })}

    var repoName: TextView? = null

    suspend fun deleteRepo(view: View) {

        repoName = findViewById(R.id.repoName)
    }

    fun addNewRepo() {

    }
}