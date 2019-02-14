package com.example.synctrue.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Entrega(
            @PrimaryKey(autoGenerate = true)
            val id: Long = 0,
            val t32_id: Int,
            val t32_endereco: String,
            val t32_cidade: String,
            val t32_valor: Float,
            val t32_peso: Float,
            val t32_empresa_id: Int,
            val t32_romaneio_id: Int)
