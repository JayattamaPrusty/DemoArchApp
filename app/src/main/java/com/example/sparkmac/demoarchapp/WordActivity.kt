package com.example.sparkmac.demoarchapp

import android.widget.Toast
import com.example.sparkmac.demoarchapp.database.databaseEntity.Word
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.design.widget.FloatingActionButton
import com.example.sparkmac.demoarchapp.viewmodel.WordViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import WordListAdapter
import android.app.Activity
import android.arch.lifecycle.Observer
import android.support.v7.widget.RecyclerView
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import com.cooltechworks.views.shimmer.ShimmerRecyclerView
import android.R.menu
import android.view.MenuInflater
import android.view.MenuItem



class WordActivity : AppCompatActivity() {

    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    private var mWordViewModel: WordViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<ShimmerRecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter(this)
        recyclerView.setAdapter(adapter)
        recyclerView.setLayoutManager(LinearLayoutManager(this))

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

        val fab = findViewById<FloatingActionButton>(R.id.fab)


        fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.delete) {

            mWordViewModel!!.deleteAll()

            return true
        }

        return super.onOptionsItemSelected(item)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.optionsmenu, menu)
        return true
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val word = Word(data!!.getStringExtra(NewWordActivity.EXTRA_REPLY))
            mWordViewModel!!.insert(word)
        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show()
        }
    }
}