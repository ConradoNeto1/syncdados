package com.example.synctrue.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Entrega(
    @PrimaryKey(autoGenerate = true)
            var id: Long = 0,
    var t32_id: Int,
    var t32_endereco: String,
    var t32_cidade: String,
    var t32_valor: Float,
    var t32_peso: Float,
    var t32_empresa_id: Int,
    var t32_romaneio_id: Int,
    var t32_data_hora_atualizacao: String)
