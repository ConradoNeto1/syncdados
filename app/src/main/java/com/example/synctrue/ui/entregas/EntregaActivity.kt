package com.example.synctrue.ui.entregas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.synctrue.R
import com.example.synctrue.models.Entrega

class EntregaActivity : AppCompatActivity(), EntregasContract.View{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrega)
    }

    override fun salvar(view: EntregasContract.View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun obterEntrega(id: Int): Entrega {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}
