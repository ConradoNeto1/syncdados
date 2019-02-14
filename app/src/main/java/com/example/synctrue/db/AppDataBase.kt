package com.example.synctrue.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.synctrue.db.dao.EntregaDAO
import com.example.synctrue.models.Entrega

@Database(entities = [Entrega::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun entregaDAO(): EntregaDAO
}