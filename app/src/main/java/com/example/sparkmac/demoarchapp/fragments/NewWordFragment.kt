package com.example.sparkmac.demoarchapp.fragments


import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.sparkmac.demoarchapp.NewWordActivity

import com.example.sparkmac.demoarchapp.R
import com.example.sparkmac.demoarchapp.WordActivity
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
class NewWordFragment : Fragment() {

    private var mEditWordView: EditText? = null

    private var mWordViewModel: WordViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_new_word, container, false)

        // Get a new or existing ViewModel from the ViewModelProvider.
        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        mEditWordView = view.findViewById(R.id.edit_word)

        val button = view.findViewById<Button>(R.id.button_save)


        button.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {

                //read value from EditText to a String variable
                val msg: String = mEditWordView?.text.toString()

                //check if the EditText have values or not
                if(msg.trim().length>0) {

                    val activity = activity
                    if (activity is WordActivity) {
                        val myactivity = activity as WordActivity?
                        myactivity!!.showWordFragment(myactivity)

                        val word = Word(mEditWordView?.text.toString())
                        mWordViewModel!!.insert(word)
                    }

                }else{

                    Toast.makeText(activity, "Please enter some message! ", Toast.LENGTH_SHORT).show()
                }




            }
        })
        return view
    }


}
