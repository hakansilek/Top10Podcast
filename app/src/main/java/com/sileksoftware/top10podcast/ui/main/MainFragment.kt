package com.sileksoftware.top10podcast.ui.main

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sileksoftware.top10podcast.R
import com.sileksoftware.top10podcast.databinding.MainFragmentBinding
import com.sileksoftware.top10podcast.model.main.PodcastModel

class MainFragment : Fragment(), ItemOnClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _mainFragmentBinding: MainFragmentBinding? = null
    private val mainFragmentBinding get() = _mainFragmentBinding!!

    private val adapter = MainAdapter()

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        adapter._itemClickListener = this
        mainFragmentBinding.podcastList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mainFragmentBinding.podcastList.adapter = adapter
        return mainFragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mainFragmentBinding = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        getData()

    }

    private fun getData(){
        val progressViewObserver = Observer<Boolean> {
            if(it)
                mainFragmentBinding.progressBar.visibility = View.VISIBLE
            else
                mainFragmentBinding.progressBar.visibility = View.INVISIBLE
        }
        val podcastObserver = Observer<List<PodcastModel>>{
            adapter.updatePodcastList(it)
        }

        val failureObserver = Observer<String> {
            showAlert(it)
        }

        viewModel.podcastList.observe(this,podcastObserver)
        viewModel.progressView.observe(this, progressViewObserver)
        viewModel.failure.observe(this, failureObserver)

        viewModel.getTop10Podcast()
    }

    private fun showAlert(message: String){
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle(R.string.x1)
        alertDialog.setMessage(message)
        alertDialog.setCancelable(false)

        alertDialog.setPositiveButton(R.string.x2){ dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.show()
    }

    override fun onItemClickListener(isLike: Boolean) {
        var message = ""
        message = if (isLike) getString(R.string.x3) else getString(R.string.x4)

        view?.let {
            val snackBar = Snackbar.make(it,message,Snackbar.LENGTH_LONG)
            snackBar.show()
        }
    }

}


