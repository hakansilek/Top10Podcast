package com.sileksoftware.top10podcats.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sileksoftware.top10podcats.R
import com.sileksoftware.top10podcats.model.main.PodcastModel

class MainAdapter(val podcasts: List<PodcastModel>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_podcast_card,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val podcast = podcasts[position]
        holder.bind(podcast)
    }

    override fun getItemCount() = podcasts.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val podcast_iv: ImageView
        private val podcastName: TextView
        private val artistName: TextView
        private val releaseDate: TextView

        init {
            podcast_iv = itemView.findViewById(R.id.podcast_iv)
            podcastName = itemView.findViewById(R.id.podcast_name_tv)
            artistName = itemView.findViewById(R.id.artist_name_tv)
            releaseDate = itemView.findViewById(R.id.release_date_tv)
        }

      fun bind(podcast: PodcastModel){
          podcastName.text = podcast.name
          artistName.text = podcast.artistName
          releaseDate.text = podcast.releaseDate

          Glide.with(itemView).load(podcast.artworkUrl100).into(podcast_iv)
      }
    }
}