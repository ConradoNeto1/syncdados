package com.example.synctrue

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.synctrue.models.Entrega
import com.example.synctrue.ui.entregas.EntregaListAdapter
import com.example.synctrue.ui.entregas.ListEntregasContract
import com.example.synctrue.ui.entregas.ListEntregasPresent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListEntregasContract.View {

    private lateinit var present
            : ListEntregasPresent
//    = ListEntregasPresent(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        present = ListEntregasPresent(this)
//        loadDataSuccess()

        val recyclerView = rv_lista_entregas
        recyclerView.adapter = listDataSuccess()?.let { EntregaListAdapter(it, this) }
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

    }

    override fun loadDataSuccess() {
        present.loadData()
    }


    override fun listDataSuccess() : List<Entrega>? {
        return present.listData()
    }
}
