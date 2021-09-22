package com.oitsystems.roomdbkotlin

import android.R.attr.button
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oitsystems.roomdbkotlin.adapter.WordListAdapter
import com.oitsystems.roomdbkotlin.application.WordsApplication
import com.oitsystems.roomdbkotlin.entity.Word
import com.oitsystems.roomdbkotlin.viewmodel.WordViewModel
import com.oitsystems.roomdbkotlin.viewmodel.WordViewModelFactory


class MainActivity : AppCompatActivity() {

    lateinit var add_details: Button
    private val newWordActivityRequestCode = 1
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        add_details = findViewById(R.id.add_details)
        
        add_details.setOnClickListener {
           // Toast.makeText(this,"You Clicked me", Toast.LENGTH_SHORT).show()

            var intent = Intent(this,AddDetailsActivity::class.java)
           // startActivityForResult(intent,newWordActivityRequestCode)
            startActivity(intent)
        }

        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            words.let { adapter.submitList(it) }
        }

        /*add_details.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // do something when the corky is clicked

                var intent = Intent(MainActivity.this,AddDetailsActivity::class.java)

            }
        })*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddDetailsActivity.EXTRA_REPLY)?.let { reply ->
                val word = Word(reply)
                wordViewModel.insert(word)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }
    }
}