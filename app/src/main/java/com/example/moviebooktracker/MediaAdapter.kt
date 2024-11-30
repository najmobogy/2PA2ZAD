package com.example.moviebooktracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MediaAdapter(
    private val mediaList: MutableList<Media>,
    private val clickListener: (Media) -> Unit
) : RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {

    class MediaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.media_title)
        val details: TextView = itemView.findViewById(R.id.media_details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.media_item, parent, false)
        return MediaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        val media = mediaList[position]
        holder.title.text = media.title
        holder.details.text = "${media.creator}, ${media.year} - Ocena: ${media.rating}"

        holder.itemView.setOnClickListener {
            clickListener(media)
        }
    }

    override fun getItemCount(): Int = mediaList.size
}