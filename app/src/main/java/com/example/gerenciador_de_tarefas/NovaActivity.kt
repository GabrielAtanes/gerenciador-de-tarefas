package com.example.gerenciador_de_tarefas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java
import android.widget.Toast

class NovaActivity : AppCompatActivity(){

    private lateinit var editTituloTarefa : EditText

    private lateinit var editDescricaoTarefa : EditText

    private lateinit var btConfirmaTarefa : Button

    private lateinit var bd : Databasehandler

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tarefa)

        editTituloTarefa = findViewById(R.id.editTituloTarefa)
        editDescricaoTarefa = findViewById(R.id.editDescricaoTarefa)
        btConfirmaTarefa = findViewById(R.id.btConfirmaTarefa)
        bd = Databasehandler (this)

        btConfirmaTarefa.setOnClickListener {

            //pega o texto, transforma em String e remove espaços do inicio e fim
            val tituloTask = editTituloTarefa.text.toString().trim()
            val descricaoTask = editDescricaoTarefa.text.toString().trim()

            if (tituloTask.isEmpty()){
                Toast.makeText(this, "Preencha o título.", Toast.LENGTH_SHORT).show()
            }
            else {
                val t = Tarefa(0, tituloTask, descricaoTask, 0)
                bd.incluirTarefa(t)

                Toast.makeText(this, "Tarefa salva com sucesso.", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, PendentesActivity::class.java))
                finish()
            }
        }
    }
}