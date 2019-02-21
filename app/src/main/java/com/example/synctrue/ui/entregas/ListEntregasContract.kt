package com.example.synctrue.ui.entregas

import android.content.Context
import android.view.View
import com.example.synctrue.models.Entrega
import com.example.synctrue.ui.base.BaseContract

interface ListEntregasContract {

    interface View: BaseContract.View {
        fun loadDataSuccess()
        fun listDataSuccess() : List<Entrega>?
    }

    interface Presenter: BaseContract.Presenter {
        fun loadData()
        fun listData() : List<Entrega>?
    }

}