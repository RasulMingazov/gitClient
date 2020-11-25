package com.example.gitclient.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.gitclient.R
import com.example.gitclient.model.pojo.NewRepo
import com.example.gitclient.repository.Repository
import com.example.gitclient.viewmodel.MainViewModel
import com.example.gitclient.viewmodel.factory.MainViewModelFactory

class NewRepoActivity: AppCompatActivity() {

    companion object {
        const val code = "code"
    }
    var tokenOfAccess = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_new_repo)


        tokenOfAccess = intent.getStringExtra(code).toString()
    }


    fun createNewRepo(view: View) {
        val repoNameET: EditText = findViewById(R.id.repoName)
        val repoName = repoNameET.text.toString()

        val descET: EditText = findViewById(R.id.descriptionText)
        val description = descET.text.toString()

        val radioGroup: RadioGroup = findViewById(R.id.radios)
        val selectedOption: Int = radioGroup!!.checkedRadioButtonId

        val radioButton: RadioButton = findViewById(selectedOption)
        val isPrivate: Boolean

        isPrivate = radioButton.text != "Public"

        val newRepo = NewRepo(repoName, isPrivate, description)
       Log.d("tagg", newRepo.toString())

        val repository = Repository()
        val viewModelFactory =
            MainViewModelFactory(repository)
        val viewModel: MainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.createRepo("token $tokenOfAccess", newRepo)
        viewModel.newRepoResponse.observe(this, Observer {responce ->
            if (responce.isSuccessful) {
                println(responce.body().toString())
                startActivity(
                    Intent(this@NewRepoActivity, RepoActivity::class.java)
                    .putExtra("code", tokenOfAccess))
            }
            else {
                Log.d("mistake", "Response error")
            }
        })
    }
}