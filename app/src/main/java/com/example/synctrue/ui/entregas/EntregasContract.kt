package com.example.synctrue.ui.entregas

import android.view.View
import com.example.synctrue.models.Entrega
import com.example.synctrue.ui.base.BaseContract

interface EntregasContract : BaseContract {

    interface View : BaseContract.View{
        fun salvar(view : View)
        fun obterEntrega(id : Int) : Entrega
    }

    interface Presenter : BaseContract.Presenter{
        fun salvar(view : View)
        fun obterEntrega(t32Id : Int) : Entrega?
    }


}