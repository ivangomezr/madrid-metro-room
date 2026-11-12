package net.azarquiel.metroroom.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.metroroom.R
import net.azarquiel.metroroom.adapters.CustomAdapterLineas
import net.azarquiel.metroroom.model.LineaView
import net.azarquiel.metroroom.util.Util
import net.azarquiel.metroroom.viewmodel.LineaViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var rvLineas: RecyclerView
    private lateinit var adapter: CustomAdapterLineas
    private lateinit var lineaViewModel: LineaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvLineas = findViewById<RecyclerView>(R.id.rvLineas)
        lineaViewModel = ViewModelProvider(this).get(LineaViewModel::class.java)
        Util.inyecta(this,"MetroDB.db")
        initRV()
        lineaViewModel.getAllLineas().observe(this, Observer { lineas ->
            // Update the cached copy of the products in the adapter.
            lineas?.let {
                adapter.setLineas(it)
            }
        })
    }

    private fun initRV() {
        adapter = CustomAdapterLineas(this, R.layout.rowlinea)
        rvLineas.layoutManager = LinearLayoutManager(this)
        rvLineas.adapter = adapter
    }

    fun onClickLinea(view: View) {
        val lineaView = view.tag as LineaView
        val intent = Intent(this, EstacionesActivity::class.java)
        intent.putExtra("linea", lineaView)
        startActivity(intent)
    }
}