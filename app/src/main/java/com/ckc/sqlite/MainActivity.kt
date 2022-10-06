package com.ckc.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ckc.sqlite.databinding.ActivityMainBinding

private lateinit var database : SQLiteDatabase
private lateinit var adapterr : adapter

private lateinit var binding: ActivityMainBinding
private lateinit var arrayList : ArrayList<Modell>
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        database =this.openOrCreateDatabase("People",Context.MODE_PRIVATE,null)


        // SQLite Read
        arrayList = ArrayList<Modell>()


        val cursor = database.rawQuery("SELECT * FROM person",null)

        val nameIndex = cursor.getColumnIndex("name")
        val idIndex = cursor.getColumnIndex("id")

        while (cursor.moveToNext()){
            val name = cursor.getString(nameIndex)
            val id = cursor.getInt(idIndex)

            val modelll = Modell(name,id)
            arrayList.add(modelll)
        }

        adapterr = adapter(arrayList)
        binding.recylerView.adapter = adapterr
        binding.recylerView.layoutManager = LinearLayoutManager(this)







    }
    fun save(view: View){
        val data = binding.dataText.text.toString()

        if (!data.isEmpty()){
        try {

            database =this.openOrCreateDatabase("People",Context.MODE_PRIVATE,null)
            database.execSQL("CREATE TABLE IF NOT EXISTS person (id INTEGER PRIMARY KEY,name VARCHAR)")

            val sqlString = "INSERT INTO person (name) VALUES (?)"
            val statement = database.compileStatement(sqlString)
            statement.bindString(1,data)

            statement.execute()
            adapterr.notifyDataSetChanged()


        }catch (e:Exception){
            e.printStackTrace()
        }
        }else{
            Toast.makeText(applicationContext,"Error !!!!!!",Toast.LENGTH_LONG).show()
        }
    }
}