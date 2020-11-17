package com.example.gitclient

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.gitclient.model.AccessToken
import com.example.gitclient.model.pojo.Repo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class webActivity: AppCompatActivity() {

    lateinit var webView: WebView

    val clientId = "f4c1bdac0b81ff49ad1b"
    val clientSecret = "eb3cbb827d1d0792b75d8c09b026db784dbc22eb"
    val redirectUri = "githubcclient://callback"
    var code = ""
    var tokenOfAccess = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web)
        createWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun createWebView() {
        webView = findViewById(R.id.webBrowser)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://github.com/login/oauth/authorize" +
                "?client_id=$clientId" +
                "&scope=repo" +
                "%20" +
                "delete_repo&" +
                "redirect_uri=$redirectUri")

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
                        authBuild(webView)

                    }
                }
            }
        }
    }

    fun authBuild(webview: WebView) {
        val client: GitHubClient = Retrofit.Builder()
                .baseUrl("https://github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitHubClient::class.java)

        val accessTokenCall: Call<AccessToken> = client.getAccessToken(
                clientId,
                clientSecret,
                code
        )
        accessTokenCall.enqueue(object : Callback<AccessToken> {
            override fun onFailure(call: Call<AccessToken>, t: Throwable) {
                println("FALSE")
            }

            override fun onResponse(call: Call<AccessToken>, response: Response<AccessToken>) {
                tokenOfAccess = response.body()?.accessToken.toString()
                startActivity(Intent(this@webActivity, repoActivity::class.java)
                    .putExtra("code", tokenOfAccess))
            }
        })
    }

}
