package com.example.todoapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
     lateinit var addItemButton: FloatingActionButton
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
     lateinit var toDoList: ArrayList<ToDo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toDoList = arrayListOf()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerViewAdapter = RecyclerViewAdapter(toDoList)
        recyclerView.adapter = recyclerViewAdapter

        addItemButton = findViewById(R.id.addButton)
        addItemButton.setOnClickListener {
            alert()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_option ->{
               deleteItem()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun alert(){
        val dialogBuilder = AlertDialog.Builder(this)
        val  input = EditText(this)
        input.hint = "Enter to-do item"
        dialogBuilder.setPositiveButton("ADD", DialogInterface.OnClickListener {
                dialog, id ->
            val toDoText = input.text.toString()
            toDoList.add(ToDo(toDoText))
            recyclerView.adapter = RecyclerViewAdapter(toDoList)
            recyclerView.adapter?.notifyDataSetChanged()
            Log.d("TAG", "alert: ${toDoList.size}")

        })
            .setNegativeButton("CANCEL", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(input)
        alert.show()
    }
    private fun deleteItem(){
        var deleteCounter = 0
        for (item in toDoList){
            if (item.isSelected)
                deleteCounter++
        }

        toDoList.removeAll { item -> item.isSelected }
        if (deleteCounter >0 ) {
            Toast.makeText(this, "$deleteCounter  deleted", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "You have no item to delete!", Toast.LENGTH_SHORT).show()
        }

        recyclerView.adapter!!.notifyDataSetChanged()

    }
}