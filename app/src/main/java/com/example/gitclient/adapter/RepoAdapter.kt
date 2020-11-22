package com.example.gitclient.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gitclient.R
import com.example.gitclient.model.pojo.Repo

class RepoAdapter(private val values: List<Repo>) :
        RecyclerView.Adapter<RepoAdapter.MyViewHolder>() {


    override fun getItemCount(): Int {
        return  values.size }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.repo, parent, false)
        return MyViewHolder(itemView)
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val repo: Repo = values[position]
        holder.name?.text = repo.name
        holder.watchers?.text = "${repo.watchers} watchers"
        holder.tags?.text = "${repo.tags} tags"
        holder.branches?.text = "${repo.branches} branches"
        holder.language?.text = repo.language
        if (repo.private) {
            holder.isPrivate?.text = "Private"
        } else {
            holder.isPrivate?.text = "Public"
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView? = itemView.findViewById(R.id.repoName)
        var branches: TextView? = itemView.findViewById(R.id.brachesQ)
        var watchers: TextView? = itemView.findViewById(R.id.watchesQ)
        var tags: TextView? = itemView.findViewById(R.id.tagsQ)
        var language: TextView? = itemView.findViewById(R.id.language)
        var isPrivate: TextView? = itemView.findViewById(R.id.isPrivate)
        var deleteImage: ImageView? = itemView.findViewById(R.id.deleteImage)
    }
}