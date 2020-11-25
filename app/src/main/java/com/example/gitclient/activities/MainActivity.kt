package com.example.gitclient.activities
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.gitclient.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startSignInActivity(view: View) {
        val secondActivity = Intent(this, WebActivity::class.java)
        startActivity(secondActivity)
    }

}