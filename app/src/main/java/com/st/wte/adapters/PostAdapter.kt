package com.st.wte.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.st.wte.databinding.PostListItemBinding
import com.st.wte.ui.main.model.Post

class PostAdapter(private val context: Context, private var posts: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
            MyViewHolder(PostListItemBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = posts[position]
        holder.binding.id.text = post.id.toString()
        holder.binding.title.text = post.title.toString()
        holder.binding.body.text = post.body.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItems(posts: List<Post>, page: Int) {
        if (page == 1) {
            this.posts = posts as ArrayList<Post>
            notifyDataSetChanged()
        } else {
            val initialIndex = itemCount
            this.posts.addAll(posts)
            notifyItemRangeInserted(initialIndex, posts.size)
        }
    }

    override fun getItemCount(): Int = posts.size
    class MyViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root)
}