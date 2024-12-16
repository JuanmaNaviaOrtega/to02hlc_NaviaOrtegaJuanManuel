package com.example.to02hlc_naviaortegajuanmanuel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.to02hlc_naviaortegajuanmanuel.R

class MenuActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val btnPrimeraApp= findViewById<Button>(R.id.btnprimeraApp)
        val btnSegundaApp= findViewById<Button>(R.id.btnsegundaApp)
        val btnTerceraApp= findViewById<Button>(R.id.btnterceraApp)
        btnPrimeraApp.setOnClickListener{navigateToprimeraApp()}
        btnSegundaApp.setOnClickListener{navigateSegundaApp()}
        btnTerceraApp.setOnClickListener{navigateTerceraApp()}

    }


    private fun navigateToprimeraApp(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateSegundaApp(){
        val intent = Intent(this, MainActivity2::class.java  )
        startActivity(intent)
    }

    private fun navigateTerceraApp(){
      val intent = Intent(this, MainActivity3::class.java  )
        startActivity(intent)
    }
}
