package com.example.gerenciador_de_tarefas

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ConcluidasActivity : AppCompatActivity() {

    private lateinit var lvConcluidas: ListView
    private lateinit var btRemover: Button
    private lateinit var bd: Databasehandler

    private var adapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_concluidas)

        lvConcluidas = findViewById(R.id.lvConcluidas)
        btRemover = findViewById(R.id.btRemover)
        bd = Databasehandler(this)

        btRemover.setOnClickListener {
            val ids = adapter?.getSelecionados() ?: emptyList()

            if (ids.isEmpty()) {
                Toast.makeText(this, "Selecione ao menos uma tarefa.", Toast.LENGTH_SHORT).show()
            } else {
                for (id in ids) {
                    bd.excluirTarefa(id)
                }
                Toast.makeText(this, "Tarefa(s) removida(s)!", Toast.LENGTH_SHORT).show()
                carregarLista()
            }
        }
    }



        override fun onResume() {
            super.onResume()
            carregarLista()
        }

        private fun carregarLista() {
            val lista = bd.listar(1)
            adapter = TarefaAdapter(this, lista)
            lvConcluidas.adapter = adapter
        }
    }

