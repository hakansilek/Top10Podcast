package com.sileksoftware.top10podcats.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sileksoftware.top10podcats.R
import com.sileksoftware.top10podcats.model.main.PodcastModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var podcastListRecyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val v = inflater.inflate(R.layout.main_fragment, container, false)
        podcastListRecyclerView = v.findViewById(R.id.podcastList)
        podcastListRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL,false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        getData()
    }

    private fun getData(){
        val podcastObserve = Observer<List<PodcastModel>>{
            podcastListRecyclerView.adapter = MainAdapter(it)
        }

        viewModel.podcastList.observe(this,podcastObserve)

        viewModel.getTop10Podcast()
    }

}


