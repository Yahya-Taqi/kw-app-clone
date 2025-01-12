package com.example.practice

import DatabaseHelper
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etStudentName = findViewById<EditText>(R.id.etStudentName)
        val etStudentAge = findViewById<EditText>(R.id.etStudentAge)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnDelete = findViewById<Button>(R.id.btnDelete)

        dbHelper = DatabaseHelper(this)

        btnAdd.setOnClickListener {
            val name = etStudentName.text.toString()
            val age = etStudentAge.text.toString().toIntOrNull()

            if (name.isNotEmpty() && age != null) {
                dbHelper.addStudent(name, age)
                Toast.makeText(this, "Student added!", Toast.LENGTH_SHORT).show()
                etStudentName.text.clear()
                etStudentAge.text.clear()
            } else {
                Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show()
            }
        }

        btnView.setOnClickListener {
            startActivity(Intent(this, ViewRecords::class.java))
        }

        btnUpdate.setOnClickListener {
            val id = findViewById<EditText>(R.id.etStudentId).text.toString().toIntOrNull()
            val name = etStudentName.text.toString()
            val age = etStudentAge.text.toString().toIntOrNull()

            if (id != null && name.isNotEmpty() && age != null) {
                val rowsUpdated = dbHelper.updateStudent(id, name, age)
                if (rowsUpdated > 0) {
                    Toast.makeText(this, "Student updated!", Toast.LENGTH_SHORT).show()
                    etStudentName.text.clear()
                    etStudentAge.text.clear()
                    findViewById<EditText>(R.id.etStudentId).text.clear()
                } else {
                    Toast.makeText(this, "Student not found!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show()
            }
        }

        btnDelete.setOnClickListener {
            val id = findViewById<EditText>(R.id.etStudentId).text.toString().toIntOrNull()

            if (id != null) {
                val rowsDeleted = dbHelper.deleteStudent(id)
                if (rowsDeleted > 0) {
                    Toast.makeText(this, "Student deleted!", Toast.LENGTH_SHORT).show()
                    findViewById<EditText>(R.id.etStudentId).text.clear()
                } else {
                    Toast.makeText(this, "Student not found!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Invalid input!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}