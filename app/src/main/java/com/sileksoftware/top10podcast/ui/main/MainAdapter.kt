package com.sileksoftware.top10podcast.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sileksoftware.top10podcast.R
import com.sileksoftware.top10podcast.model.main.PodcastModel

class MainAdapter: RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    var _itemClickListener: ItemOnClickListener? = null
    private val itemClickListener get() = _itemClickListener!!

    private var podcastList = mutableListOf<PodcastModel>()
    fun updatePodcastList(podcastList: List<PodcastModel>){
        this.podcastList.clear()
        this.podcastList.addAll(podcastList)
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_podcast_card,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val podcast = podcastList[position]
        holder.bind(podcast, itemClickListener)
    }

    override fun getItemCount() = podcastList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val podcastCover: ImageView = itemView.findViewById(R.id.podcast_iv)
        private val podcastName: TextView = itemView.findViewById(R.id.podcast_name_tv)
        private val artistName: TextView = itemView.findViewById(R.id.artist_name_tv)
        private val releaseDate: TextView = itemView.findViewById(R.id.release_date_tv)

        private val likeButton: ImageButton = itemView.findViewById(R.id.like_btn)
        private val dislikeButton: ImageButton = itemView.findViewById(R.id.dislike_btn)

        init {
            podcastCover.clipToOutline = true
        }

      fun bind(podcast: PodcastModel, itemClickListener: ItemOnClickListener){
          podcastName.text = podcast.name
          artistName.text = podcast.artistName
          releaseDate.text = podcast.releaseDate

          Glide.with(itemView).load(podcast.artworkUrl100).into(podcastCover)

          likeButton.setOnClickListener { itemClickListener.onItemClickListener(true) }
          dislikeButton.setOnClickListener { itemClickListener.onItemClickListener(false) }
      }
    }
}