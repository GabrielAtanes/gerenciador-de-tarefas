package com.example.gerenciador_de_tarefas

data class Tarefa (
    var id         : Int,
    var titulo     : String,
    var descricao  : String,
    var concluida  : Int            // 0 = pendente, 1 = conclúida
)