package com.example.gerenciador_de_tarefas

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Databasehandler(context: Context) : SQLiteOpenHelper(context, "bdTarefas", null, 1) {

    //roda só uma vez, quando nao existe arquivo do banco
    override fun onCreate(bd:SQLiteDatabase?) {
        bd?.execSQL("CREATE TABLE tarefas(" +
                    "_id        INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "titulo    TEXT, " +
                    "descricao TEXT, " +
                    "concluida INTEGER)")
    }

    //roda quando voce aumenta a versao (o 1 la em cima)
    override fun onUpgrade(bd: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            bd?.execSQL("DROP TABLE IF EXISTS tarefas")
            onCreate(bd)
    }

    fun incluirTarefa(t: Tarefa) {
        val bd = this.writableDatabase
        val valores = ContentValues()
        valores.put("titulo", t.titulo)
        valores.put("descricao", t.descricao)
        valores.put("concluida",0)
        bd.insert("tarefa", null, valores)
        bd.close()
     }

    // filtro: 0 = pendentes, 1 = concluídas
    fun listar(filtro: Int): MutableList<Tarefa> {
        val bd = this.readableDatabase
        val cursor = bd.query(
            "tarefa",
            null,
            "concluida = ?",
            arrayOf(filtro.toString()),
            null,
            null,
            null
        )

        val lista = mutableListOf<Tarefa>()
        while (cursor.moveToNext()) {
            lista.add(Tarefa(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2) ?: "",
                cursor.getInt(3)
            ))
        }
        cursor.close()
        return lista
    }

    fun concluirTarefa(id: Int) {
        val bd = this.writableDatabase
        val valores = ContentValues()
        valores.put("concluida", 1)
        bd.update("tarefa", valores, "_id = ?", arrayOf(id.toString()))
        bd.close()
    }

    fun excluirTarefa(id: Int) {
        val bd = this.writableDatabase
        bd.delete("tarefa", "_id = ?", arrayOf(id.toString()))
        bd.close()
    }
}