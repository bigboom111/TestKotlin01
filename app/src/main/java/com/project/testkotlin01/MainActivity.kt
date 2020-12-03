package com.project.testkotlin01

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    final val TASK_NAME_REQUEST_CODE = 100

    final val PREF_NAME = "task_list"
    final val TASK_LIST_PREF_KEY = "items"

    val item = ArrayList<String>()
    var adapter : ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item.add("Hello")
        item.add("World")

        adapter = ArrayAdapter<String>(this , android.R.layout.simple_list_item_1 , item)

        itemListView.adapter = adapter
    }

    public fun sayGoodBye(view: View) {
       /* val textView : TextView = findViewById(R.id.text1);
        textView.text = "Good bye!!";*/
        //text1.text = "Good bye!!"
    }

    fun saveButtonClick(view: View) {
        /*val msg = itemEdit.text.toString();
        Toast.makeText(this , msg , Toast.LENGTH_LONG).show()
        itemEdit.text.clear()
        item.add(msg)
        adapter?.notifyDataSetChanged()*/
    }

    override fun onStart() {
        super.onStart()
        restoreTaskList()
    }

    override fun onStop() {
        super.onStop()
        saveTaskList()
    }

    private fun saveTaskList() {
        val builder = StringBuilder()

        for (st in item){
            builder.append(st)
            builder.append(",")
        }
        val data = builder.toString()

        val preference = getSharedPreferences(PREF_NAME , Context.MODE_PRIVATE)

        val editor = preference.edit()
        editor.putString(TASK_LIST_PREF_KEY , data)
        editor.commit()
    }

    private fun restoreTaskList() {
        val preference = getSharedPreferences(PREF_NAME , Context.MODE_PRIVATE)
        val data = preference.getString(TASK_LIST_PREF_KEY , null)
        if (data != null){
            item.clear()
            for (st in data.split(",")){
                if (st != ""){
                    item.add(st)
                }
            }
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            if (data != null){
                val taskName = data.getStringExtra("TASK_NAME_KEY")
                if (taskName != null){
                    item.add(taskName)
                    adapter?.notifyDataSetChanged()
                }
            }
        }
        /*else{
            item.add("cancelled")
            adapter?.notifyDataSetChanged()
        }*/
    }

    fun newClick(view: View) {
        val intent = Intent(this , InputActivity::class.java)
        //startActivity(intent)
        startActivityForResult(intent , TASK_NAME_REQUEST_CODE)
    }
}