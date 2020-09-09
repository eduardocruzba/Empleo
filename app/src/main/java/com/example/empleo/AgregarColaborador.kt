package com.example.empleo

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.colaboradores_main.*

class AgregarColaborador : AppCompatActivity(){

    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.colaboradores_main)

        try {
            var bundle: Bundle? = intent.extras
            id = bundle?.getInt("MainActId", 0)!!
            if (id != 0) {
                nombre.setText(bundle.getString("MainActTitle"))
                contacto.setText(bundle.getString("MainActContent"))
            }
        } catch (ex: Exception) {
        }

        btadd.setOnClickListener {
            var dbManager = DbManager(this)

            var values = ContentValues()
            values.put("Nombre", nombre.text.toString())
            values.put("Contacto", contacto.text.toString())

            if (id == 0) {
                val mID = dbManager.insert(values)

                if (mID > 0) {
                    Toast.makeText(this, "Add note successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Fail to add note!", Toast.LENGTH_LONG).show()
                }
            } else {
                var selectionArs = arrayOf(id.toString())
                val mID = dbManager.update(values, "Id=?", selectionArs)

                if (mID > 0) {
                    Toast.makeText(this, "Add note successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Fail to add note!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}