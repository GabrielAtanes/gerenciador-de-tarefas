package com.example.gerenciador_de_tarefas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java

class MenuActivity : AppCompatActivity(){

    private lateinit var btNova: Button
    private lateinit var btPendentes: Button
    private lateinit var btConcluidas: Button

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        btNova = findViewById(R.id.btNova)
        btPendentes = findViewById(R.id.btPendentes)
        btConcluidas = findViewById(R.id.btConcluidas)

        btNova.setOnClickListener {
            startActivity(Intent(this, NovaActivity::class.java))
        }

        btPendentes.setOnClickListener {
            startActivity(Intent(this,PendentesActivity::class.java))
        }

        btConcluidas.setOnClickListener {
            startActivity(Intent(this,ConcluidasActivity::class.java))
        }

    }




}