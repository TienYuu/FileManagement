package com.example.filemanagement

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file_list)
        var path = getIntent().getStringExtra("path");
        if (path==null) {
        path=Environment.getExternalStorageDirectory().absolutePath
        }
        val view = findViewById<RecyclerView>(R.id.recycler)
        val nofiletext = findViewById<TextView>(R.id.nofiles_textview)

        var root = File(path)
        var filelist = root.listFiles()

        if(filelist==null || filelist.size ==0){
            nofiletext.setVisibility(View.VISIBLE);
            return;
        }
        nofiletext.setVisibility(View.INVISIBLE)
        view.setLayoutManager(LinearLayoutManager(this))
        view.setAdapter(MyAdapter(applicationContext, filelist))
    }
}