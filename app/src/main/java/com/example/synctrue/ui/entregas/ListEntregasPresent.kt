package com.example.synctrue.ui.entregas

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import com.example.synctrue.api.ApiInterface
import com.example.synctrue.db.AppDataBase
import com.example.synctrue.models.Entrega
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListEntregasPresent : ListEntregasContract.Presenter {


    private lateinit var listEntregasView : ListEntregasContract.View
    private val api : ApiInterface = ApiInterface.create()
    private var dataBase : AppDataBase? = null


    constructor(context: Context){
        dataBase = Room.databaseBuilder(context, AppDataBase::class.java, "syncDados-database")
            .allowMainThreadQueries()
            .build()
    }

    override fun loadData() {

        val call =  api.getEntregasPorEmpresaRomaneio(4, 665787)
        var entregaDao = dataBase?.entregaDAO()

        call.enqueue(object: Callback<List<Entrega>?>{
            override fun onFailure(call: Call<List<Entrega>?>, t: Throwable) {
                Log.i("SyncTrue", t.message)
            }

            override fun onResponse(call: Call<List<Entrega>?>, response: Response<List<Entrega>?>) {
               response.body()?.let {
                   Log.i("SyncTrue", "loadData onResponse")
                   val entregasRetorno: List<Entrega> = it

                   for(entrega in entregasRetorno){
                       entregaDao?.addEntrega(entrega)
                       Log.i("SyncTrue", "loop onResponse")
                   }
               }
            }

        })
    }

//    override fun getDataBase(context: Context) {
//        dataBase = Room.databaseBuilder(context, AppDataBase::class.java, "syncDados-database").build()
//    }

    override fun listData(): List<Entrega>? {
        var entregaDAO = dataBase?.entregaDAO()
        var entregas = entregaDAO?.allEntregas()

        if (entregas != null) {
            for(entrega in entregas){
                Log.i("SyncTrue", "loop entregas")
            }
        }

        return entregas

    }

}