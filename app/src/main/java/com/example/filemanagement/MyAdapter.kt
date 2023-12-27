package com.example.filemanagement

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MyAdapter(private val context: Context, private val filesAndFolders: Array<File>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.file_name_text_view)
        val imageView: ImageView = view.findViewById(R.id.icon_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
        
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectedFile = filesAndFolders[position]
        holder.textView.text = selectedFile.name

        holder.imageView.setImageResource(if (selectedFile.isDirectory) R.drawable.ic_baseline_folder_24 else R.drawable.ic_baseline_insert_drive_file_24)

        holder.itemView.setOnClickListener {
            if (selectedFile.isDirectory) {
                val intent = Intent(context, MainActivity::class.java)
                intent.putExtra("path", selectedFile.absolutePath)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            } else {
                try {
                    val intent = Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse(selectedFile.absolutePath)
                        type = "*/*"
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, "Cannot open the file", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun getItemCount() = filesAndFolders.size
}