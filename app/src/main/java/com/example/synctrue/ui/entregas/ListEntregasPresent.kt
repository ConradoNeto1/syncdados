package com.example.synctrue.ui.entregas

import android.util.Log
import com.example.synctrue.api.ApiInterface
import com.example.synctrue.models.Entrega
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListEntregasPresent : ListEntregasContract.Presenter {

    private lateinit var listEntregasView : ListEntregasContract.View
    private val api : ApiInterface = ApiInterface.create()

    override fun loadData() {

        val call =  api.getEntregasPorEmpresaRomaneio(4, 665787)

        call.enqueue(object: Callback<List<Entrega>?>{
            override fun onFailure(call: Call<List<Entrega>?>, t: Throwable) {
                Log.i("SyncTrue", t.message)
            }

            override fun onResponse(call: Call<List<Entrega>?>, response: Response<List<Entrega>?>) {
               response.body()?.let {
                   Log.i("SyncTrue", "loadData onResponse")
                   val entregasRetorno: List<Entrega> = it

                   for(entrega in entregasRetorno){
                       Log.i("SyncTrue", "loop onResponse")
                   }
               }
            }

        })
    }


}