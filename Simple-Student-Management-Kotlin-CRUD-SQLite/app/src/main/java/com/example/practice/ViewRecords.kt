package com.example.practice

import DatabaseHelper
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ViewRecords : AppCompatActivity()
{

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_records)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.secondScreen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val listViewStudents = findViewById<ListView>(R.id.listViewStudents)
        dbHelper = DatabaseHelper(this)

        val students = dbHelper.getAllStudents()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students)
        listViewStudents.adapter = adapter

    }
}