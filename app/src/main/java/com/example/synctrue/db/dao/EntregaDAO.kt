package com.example.synctrue.db.dao

import android.arch.persistence.room.*
import com.example.synctrue.models.Entrega

@Dao
interface EntregaDAO {

    @Insert
    fun addEntrega(vararg entrega: Entrega)

    @Query("SELECT * FROM Entrega")
    fun allEntregas() : List<Entrega>

    @Delete
    fun deleteEntrega(vararg entrega: Entrega)

    @Update
    fun updateEntrega(vararg entrega: Entrega)

    @Query("SELECT * FROM Entrega WHERE t32_id = :t32_id")
    fun getEntregaByT32Id(vararg t32_id: Int) : Entrega
}