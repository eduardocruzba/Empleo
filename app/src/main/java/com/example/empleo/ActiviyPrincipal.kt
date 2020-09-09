package com.example.empleo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import kotlinx.android.synthetic.main.activiy_principal.*

class ActiviyPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activiy_principal)
        agregarColaborador()
        verColaboradores()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.cerrar_sesion, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.cs -> {
            signOut()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }



    fun signOut(){

            progressBar.visibility = View.VISIBLE
            AuthUI.getInstance().signOut(this).addOnSuccessListener {
                startActivity(Intent(this,Login::class.java))
                Toast.makeText(this,"Hasta pronto",Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener {
                progressBar.visibility = View.GONE
                Toast.makeText(this,"Ocurrio un error ${it.message}",Toast.LENGTH_SHORT).show()
        }
    }

    fun agregarColaborador(){
        btn_2.setOnClickListener {
            var ir = Intent(this,AgregarColaborador::class.java)
            startActivity(ir)
        }
    }

    fun verColaboradores(){
        btn_1.setOnClickListener {
            var ir =Intent(this,listadocolaborador::class.java)
            startActivity(ir)
        }
    }
}
