package com.example.sparkmac.demoarchapp.fragments


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cooltechworks.views.shimmer.ShimmerRecyclerView

import com.example.sparkmac.demoarchapp.R
import com.example.sparkmac.demoarchapp.adapterall.WordListAdapter
import com.example.sparkmac.demoarchapp.database.databaseEntity.Word
import com.example.sparkmac.demoarchapp.viewmodel.WordViewModel


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class WordFragment : Fragment() {

    private var mWordViewModel: WordViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_word, container, false)

        val recyclerView = view.findViewById<ShimmerRecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(activity)
        recyclerView.setAdapter(adapter)
        recyclerView.setLayoutManager(LinearLayoutManager(activity))

        // Get a new or existing ViewModel from the ViewModelProvider.
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mWordViewModel!!.getAllWords().observe(this, Observer<List<Word>> { words ->
            // Update the cached copy of the words in the adapter.
            if (words != null) {
                adapter.setWords(words)
            }
        })
        return view
    }


}
