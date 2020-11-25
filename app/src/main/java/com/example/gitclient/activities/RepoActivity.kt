package com.example.gitclient.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gitclient.R
import com.example.gitclient.adapter.CellClickListener
import com.example.gitclient.adapter.RepoAdapter
import com.example.gitclient.model.pojo.Repo
import com.example.gitclient.repository.Repository
import com.example.gitclient.viewmodel.MainViewModel
import com.example.gitclient.viewmodel.factory.MainViewModelFactory


class RepoActivity: AppCompatActivity(), CellClickListener {

    companion object {
        const val code = "code"
    }

    var tokenOfAccess = ""

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.repos)

        tokenOfAccess = intent.getStringExtra(code).toString()
        createRecycleView()
    }

    private fun createRecycleView() {  val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRepos)
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
                recyclerView.adapter = responce.body()?.let { RepoAdapter(it, this) }
            }
        })}

    fun newRepo(view: View) {
        startActivity(
                Intent(this, NewRepoActivity::class.java).putExtra("code",tokenOfAccess))
    }

    fun updateRepos(view: View) {
        val intent = intent
        finish()
        startActivity(intent)
    }

    override fun onCellClickListener(repo: Repo) {
        val repository = Repository()
        val viewModelFactory =
                MainViewModelFactory(repository)
        val viewModel: MainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.deleteRepo("token $tokenOfAccess", repo.owner.login, repo.name)
        viewModel.deleteRepoResponse.observe(this, Observer {responce ->
            if (responce.isSuccessful) {
                Toast.makeText(this, "${repo.name} is deleted", Toast.LENGTH_SHORT).show()
            }
            else {
                Log.d("mistake", "Response error")
            }
        })
    }
}