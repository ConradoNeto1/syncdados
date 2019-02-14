package com.example.synctrue.api

import com.example.synctrue.models.Entrega
import com.example.synctrue.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("entregas/{empresa_id}/{romaneio_id}")
    fun getEntregasPorEmpresaRomaneio(@Path("empresa_id") empresaId: Int, @Path("romaneio_id") romaneioId: Int): Call<List<Entrega>>

    companion object Factory {
        fun create(): ApiInterface {
            val retorfit  = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retorfit.create(ApiInterface::class.java)
        }
    }
}