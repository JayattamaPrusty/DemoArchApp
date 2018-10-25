package com.example.sparkmac.demoarchapp.adapterall
import android.content.Context
import com.example.sparkmac.demoarchapp.database.databaseEntity.Word
import android.widget.TextView
import android.support.v7.widget.RecyclerView


import android.view.*

import com.example.sparkmac.demoarchapp.R


class WordListAdapter internal constructor(context: Context?) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val mInflater: LayoutInflater
    private var mWords: List<Word>? = null // Cached copy of words

    inner class WordViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public val wordItemView: TextView

        init {
            wordItemView = itemView.findViewById(R.id.textView)
        }
    }

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (mWords != null) {
            val current = mWords!![position]
            holder.wordItemView.setText(current.getWord())
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.text = "No Word"
        }
    }

    internal fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    override fun getItemCount(): Int {
        return if (mWords != null)
            mWords!!.size
        else
            0
    }
}