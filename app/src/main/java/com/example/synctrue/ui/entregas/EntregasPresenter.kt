package com.example.synctrue.ui.entregas

import android.arch.persistence.room.Room
import android.content.Context
import com.example.synctrue.db.AppDataBase
import com.example.synctrue.models.Entrega

class EntregasPresenter : EntregasContract.Presenter {

    private var dataBase : AppDataBase? = null

    constructor(context: Context){
        dataBase = Room.databaseBuilder(context, AppDataBase::class.java, "syncDados-database")
            .allowMainThreadQueries()
            .build()
    }

    override fun salvar(view: EntregasContract.View) {
        var entregaDAO = dataBase?.entregaDAO()
        entregaDAO?.addEntrega()
    }

    override fun obterEntrega(t32Id: Int): Entrega? {
        var entregaDAO = dataBase?.entregaDAO()
        var entrega = entregaDAO?.getEntregaByT32Id(t32Id)

        return entrega
    }
}