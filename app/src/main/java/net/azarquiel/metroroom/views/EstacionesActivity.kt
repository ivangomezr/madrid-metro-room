package net.azarquiel.metroroom.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.metroroom.R
import net.azarquiel.metroroom.model.Estacion
import net.azarquiel.metroroom.model.EstacionView
import net.azarquiel.metroroom.model.LineaView
import net.azarquiel.metroroom.viewmodel.EstacionViewModel
import net.azarquiel.recyclerclase.adapters.CustomAdapterEstaciones

class EstacionesActivity : AppCompatActivity() {
    private lateinit var adapter: CustomAdapterEstaciones
    private lateinit var rvEstaciones: RecyclerView
    private lateinit var lineaView: LineaView
    private lateinit var estacionViewModel: EstacionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estaciones)
        rvEstaciones = findViewById<RecyclerView>(R.id.rvEstaciones)
        lineaView = intent.getSerializableExtra("linea") as LineaView
        title = "${lineaView.nombre} (${lineaView.inifin})"

        initRV()
        estacionViewModel = ViewModelProvider(this).get(EstacionViewModel::class.java)
        estacionViewModel.getEstacionesByLinea(lineaView.id).observe(this, Observer { estaciones ->
            // Update the cached copy of the products in the adapter.
            estaciones?.let {
                getCorrespondencias(it)
            }
        })
    }

    private fun initRV() {
        adapter = CustomAdapterEstaciones(this, R.layout.rowestaciones, lineaView.color)
        rvEstaciones.layoutManager = LinearLayoutManager(this)
        rvEstaciones.adapter = adapter
    }

    private fun getCorrespondencias(estaciones: List<Estacion>) {
        val estacionesView = ArrayList<EstacionView>()

        GlobalScope.launch() {
            for (estacion in estaciones) {
                estacionesView.add(
                    EstacionView(
                        estacion.id,
                        estacion.nombre,
                        estacionViewModel.getCorrespondencias(estacion.nombre, lineaView.id)
                    )
                )
            }
            launch(Main) {
                adapter.setEstaciones(estacionesView)
            }
        }

    }
}