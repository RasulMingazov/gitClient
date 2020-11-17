package com.example.gitclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitclient.R
import com.example.gitclient.model.pojo.Repo

class RepoAdapter(private val values: List<Repo>) :
        RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {


    override fun getItemCount(): Int {
        println("AAAAA" + values.size)
        return  values.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.repo, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repo: Repo = values[position]
        holder.textOfRepo?.text = repo.name

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textOfRepo: TextView? = null

        init {
            textOfRepo = itemView?.findViewById(R.id.repoName)
        }
    }
}