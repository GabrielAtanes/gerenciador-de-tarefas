package com.example.gerenciador_de_tarefas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView

class TarefaAdapter(context: Context, private val lista: MutableList<Tarefa>):
        ArrayAdapter<Tarefa>(context, 0, lista) {

    //guarda os ids das tarefas marcadas
    private val selecionados = mutableSetOf<Int>()

    override fun getView(posicao: Int, convertView: View?, parent: ViewGroup): View {
        val card = LayoutInflater.from(context).inflate(R.layout.layout_card, parent, false)

        val tvTitulo: TextView = card.findViewById(R.id.tvTitulo)
        val tvDescricao: TextView = card.findViewById(R.id.tvDescricao)
        val cbSelecionar: CheckBox = card.findViewById(R.id.cbSelecionar)

        val tarefa = lista[posicao]
        tvTitulo.text = tarefa.titulo

        if (tarefa.descricao == "") {
            tvDescricao.visibility = View.GONE
        } else {
            tvDescricao.text = tarefa.descricao
        }

        //Primeiro mostra o edtado atual da checkbox, depois liga o listener
        cbSelecionar.isChecked = selecionados.contains(tarefa.id)

        cbSelecionar.setOnCheckedChangeListener { _, marcada ->
            if (marcada) selecionados.add(tarefa.id) else selecionados.remove(tarefa.id)
        }

        return card
    }

    fun getSelecionados(): List<Int> {
        return selecionados.toList()
    }
}