package com.example.gitclient.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.gitclient.repository.Repository
import com.example.gitclient.viewmodel.MainViewModel
import com.example.gitclient.viewmodel.factory.MainViewModelFactory
import androidx.lifecycle.Observer
import com.example.gitclient.R

class WebActivity: AppCompatActivity() {

    lateinit var webView: WebView

    val clientId = ""
    val clientSecret = ""
    val redirectUri = "githubcclient://callback"
    var code = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web)
        createWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun createWebView() {
        webView = findViewById(R.id.webBrowser)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(
            "https://github.com/login/oauth/authorize" +
                    "?client_id=$clientId" +
                    "&scope=repo" +
                    "%20" +
                    "delete_repo&" +
                    "redirect_uri=$redirectUri"
        )
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (url != null) {
                    if (url.startsWith(redirectUri)) {
                        code = url.split("code=")[1]
                        authBuild()
                    }
                }
            }
        }
    }

    fun authBuild() {
        val repository = Repository()
        val viewModelFactory =
            MainViewModelFactory(repository)
        val viewModel: MainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getAccessToken(clientId, clientSecret, code)
        viewModel.authResponse.observe(this, Observer {responce ->
            if (responce.isSuccessful) {
            startActivity(Intent(this@WebActivity, RepoActivity::class.java)
                        .putExtra("code", responce.body()?.accessToken.toString()))
            }
            else {
                Log.d("mistake", "Response error")
            }
        })
    }
}
