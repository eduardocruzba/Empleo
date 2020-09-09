package com.example.empleo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.lista.*

class listadocolaborador: AppCompatActivity() {

    private var listNotes = ArrayList<Colaborador>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lista)


        loadQueryAll()

        lvNotes.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                Toast.makeText(this, "" + listNotes[position].title, Toast.LENGTH_SHORT)
                    .show()

                val ir = Intent(this,MapsActivity::class.java)
                startActivity(ir)
            }
        

    }


    override fun onResume() {
        super.onResume()
        loadQueryAll()
    }

    fun loadQueryAll() {

        var dbManager = DbManager(this)
        val cursor = dbManager.queryAll()

        listNotes.clear()
        if (cursor.moveToFirst()) {

            do {
                val id = cursor.getInt(cursor.getColumnIndex("Id"))
                val title = cursor.getString(cursor.getColumnIndex("Nombre"))
                val content = cursor.getString(cursor.getColumnIndex("Contacto"))

                listNotes.add(Colaborador(id, title, content))

            } while (cursor.moveToNext())
        }

        var notesAdapter = NotesAdapter(this, listNotes)
        lvNotes.adapter = notesAdapter
    }

    inner class NotesAdapter : BaseAdapter {

        private var notesList = ArrayList<Colaborador>()
        private var context: Context? = null

        constructor(context: Context, notesList: ArrayList<Colaborador>) : super() {
            this.notesList = notesList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.colaborador, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
                Log.i("JSA", "set Tag for ViewHolder, position: " + position)
            } else {
                view = convertView
                vh = view.tag as ViewHolder
            }

            var mNote = notesList[position]

            vh.tvTitle.text = mNote.title
            vh.tvContent.text = mNote.content




            return view
        }


        override fun getItem(position: Int): Any {
            return notesList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return notesList.size
        }

    }

    private class ViewHolder(view: View?) {
        val tvTitle: TextView
        val tvContent: TextView

        init {
            this.tvTitle = view?.findViewById(R.id.tvTitle) as TextView
            this.tvContent = view?.findViewById(R.id.tvContent) as TextView

        }


    }
}