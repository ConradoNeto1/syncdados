package com.example.synctrue.services

import android.app.Service
import android.arch.persistence.room.Room
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.synctrue.api.ApiInterface
import com.example.synctrue.db.AppDataBase
import com.example.synctrue.db.dao.EntregaDAO
import com.example.synctrue.models.Entrega
import com.example.synctrue.ui.entregas.ListEntregasPresent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EntregasService : Service() {

    private var dataBase : AppDataBase? = null
    private val api : ApiInterface = ApiInterface.create()

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.i("SyncTrue", "onStartCommand")
        loadData()

        return super.onStartCommand(intent, flags, startId)

//        return START_STICKY
    }

    fun loadData() {

        dataBase = Room.databaseBuilder(this, AppDataBase::class.java, "syncDados-database")
            .allowMainThreadQueries()
            .build()

        val call =  api.getEntregasPorEmpresaRomaneio(4, 665787)
        var entregaDao = dataBase?.entregaDAO()

        call.enqueue(object: Callback<List<Entrega>?> {
            override fun onFailure(call: Call<List<Entrega>?>, t: Throwable) {
                Log.i("SyncTrue", t.message)
            }

            override fun onResponse(call: Call<List<Entrega>?>, response: Response<List<Entrega>?>) {
                response.body()?.let {
                    Log.i("SyncTrue", "loadData onResponse")
                    val entregasRetorno: List<Entrega> = it

                    for(entrega in entregasRetorno){
                        if (entregaDao != null) {
                            verificaAtualizacao(entrega, entregaDao)
                        }
//                       entregaDao?.addEntrega(entrega)
                        Log.i("SyncTrue", "loop onResponse")
                    }
                }
            }

        })
    }

    fun verificaAtualizacao(entrega: Entrega, entregaDAO: EntregaDAO) {
        val entregaBancoLocal = entregaDAO.getEntregaByT32Id(entrega.t32_id)

        if(entregaBancoLocal == null){
            entregaDAO.addEntrega(entrega)
            Log.i("SyncTrue", "Insert")
        }else if(!entregaBancoLocal.t32_data_hora_atualizacao.equals(entrega.t32_data_hora_atualizacao)){

            entrega.id = entregaBancoLocal.id

            entregaDAO.updateEntrega(entrega)
            Log.i("SyncTrue", "Update")
        }
    }
}

