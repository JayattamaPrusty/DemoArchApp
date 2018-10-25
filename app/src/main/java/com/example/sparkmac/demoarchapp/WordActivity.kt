package com.example.sparkmac.demoarchapp

import android.widget.Toast
import com.example.sparkmac.demoarchapp.database.databaseEntity.Word
import android.content.Intent
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.design.widget.FloatingActionButton
import com.example.sparkmac.demoarchapp.viewmodel.WordViewModel
import android.arch.lifecycle.ViewModelProviders

import android.app.Activity

import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View

import android.view.MenuItem
import com.example.sparkmac.demoarchapp.fragments.NewWordFragment
import com.example.sparkmac.demoarchapp.fragments.WordFragment


class WordActivity : AppCompatActivity() {

    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    private var mWordViewModel: WordViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_word)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        showWordFragment(this)


        // Get a new or existing ViewModel from the ViewModelProvider.
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)



        val fab = findViewById<FloatingActionButton>(R.id.fab)


        fab.setOnClickListener(View.OnClickListener {
           /* val intent = Intent(this, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)*/

            showNewWordFragment(this)
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



     fun showWordFragment( activity:Activity){

        val theFragment = WordFragment()


       // val theTag = String.format(TAG_FRAGMENT, mFragmentNum)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_word, theFragment)
        transaction.commit()


    }


    fun showNewWordFragment( activity:Activity){

        val theFragment = NewWordFragment()


        // val theTag = String.format(TAG_FRAGMENT, mFragmentNum)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.content_word, theFragment)
        transaction.commit()


    }
}