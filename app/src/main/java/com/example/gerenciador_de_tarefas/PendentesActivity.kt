package com.example.gerenciador_de_tarefas

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class PendentesActivity : AppCompatActivity() {

    private lateinit var lvPendentes: ListView
    private lateinit var btConcluir: Button
    private lateinit var bd: Databasehandler
    private var adapter: TarefaAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pendentes)

        lvPendentes = findViewById(R.id.lvPendentes)
        btConcluir = findViewById(R.id.btConcluir)
        bd = Databasehandler(this)

        btConcluir.setOnClickListener {
            val ids = adapter?.getSelecionados() ?: emptyList()

            if (ids.isEmpty()) {
                Toast.makeText(this, "Selecione ao menos uma tarefa.", Toast.LENGTH_SHORT).show()
            } else {
                for (id in ids) {
                    bd.concluirTarefa(id)
                }
                Toast.makeText(this, "Tarefa(s) concluída(s)!", Toast.LENGTH_SHORT).show()
                carregarLista()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        carregarLista()
    }

    private fun carregarLista() {
        val lista = bd.listar(0)               // 0 = pendentes
        adapter = TarefaAdapter(this, lista)   // adapter novo = seleção zerada
        lvPendentes.adapter = adapter
    }
}

