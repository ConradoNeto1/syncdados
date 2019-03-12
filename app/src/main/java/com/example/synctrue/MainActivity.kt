package com.example.synctrue

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.synctrue.models.Entrega
import com.example.synctrue.services.EntregasService
import com.example.synctrue.ui.entregas.EntregaListAdapter
import com.example.synctrue.ui.entregas.ListEntregasContract
import com.example.synctrue.ui.entregas.ListEntregasPresent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListEntregasContract.View {

    private lateinit var present
            : ListEntregasPresent
//    = ListEntregasPresent(this)

    private lateinit var pullRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        present = ListEntregasPresent(this)
//        loadDataSuccess()

        setAlarm()

        val recyclerView = rv_lista_entregas
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = listDataSuccess()?.let { EntregaListAdapter(it, this) }
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        pullRefresh = findViewById(R.id.pullRefresh)
        pullRefresh.setOnRefreshListener {
            recyclerView.adapter = listDataSuccess()?.let { EntregaListAdapter(it, this) }
            recyclerView.layoutManager = layoutManager

            pullRefresh.isRefreshing = false
        }

    }

    override fun loadDataSuccess() {
        present.loadData()
    }


    override fun listDataSuccess() : List<Entrega>? {
        return present.listData()
    }

    fun setAlarm(){
        Log.i("SyncTrue", "setAlarm")
        val alarm = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, EntregasService::class.java)

        val pendingIntent = PendingIntent.getService(this, 0, intent, 0)

        alarm.setInexactRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            5000,
            20000,
            pendingIntent
        )

    }

    fun cancelAlarm(){
        Log.i("SyncTrue", "cancelAlarm")
        val intent = Intent(this, EntregasService::class.java)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val pendingIntent = PendingIntent.getService(this, 0, intent, 0)

        alarmManager.cancel(pendingIntent)
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelAlarm()
    }
}
