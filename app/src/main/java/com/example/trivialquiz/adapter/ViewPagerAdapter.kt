package com.example.trivialquiz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.trivialquiz.R
import com.example.trivialquiz.model.ImageModel
import kotlinx.android.synthetic.main.item_viewpager.view.*


class ViewPagerAdapter(
    private val listUrl: ArrayList<ImageModel>,
    private val context: Context
) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_viewpager, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listUrl.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(listUrl[position].url)
            .fitCenter()
            .into(holder.imageView)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.img_view_pager
    }

}
