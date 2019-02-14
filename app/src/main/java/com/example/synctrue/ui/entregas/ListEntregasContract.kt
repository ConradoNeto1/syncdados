package com.example.synctrue.ui.entregas

import com.example.synctrue.ui.base.BaseContract

interface ListEntregasContract {

    interface View: BaseContract.View {
        fun loadDataSuccess()
    }

    interface Presenter: BaseContract.Presenter {
        fun loadData()
    }

}