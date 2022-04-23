package com.application.lab1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.social_item.view.*

class SocialAdapter(val context: Context, val list: ArrayList<SocialData>, val click: onClick) :
    RecyclerView.Adapter<SocialAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(context).inflate(R.layout.social_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.text = list[position].title
        Glide.with(context).load(list[position].icon).into(holder.img)

        holder.card.setOnClickListener {
            click.onClickItem(holder.adapterPosition)
        }
    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val img = item.img
        val title = item.tv_title
        val card = item.cardview


    }

    interface onClick {
        fun onClickItem(position: Int)
    }
}