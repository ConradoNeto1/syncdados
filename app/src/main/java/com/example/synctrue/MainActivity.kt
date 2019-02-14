package com.example.synctrue

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.synctrue.ui.entregas.ListEntregasContract
import com.example.synctrue.ui.entregas.ListEntregasPresent

class MainActivity : AppCompatActivity(), ListEntregasContract.View {

    private val present : ListEntregasPresent = ListEntregasPresent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadDataSuccess()

    }

    override fun loadDataSuccess() {
        present.loadData()
    }


}
