package net.azarquiel.metroroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import net.azarquiel.metroroom.model.*

class LineaViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: LineaRepository = LineaRepository(application)

    fun getAllLineas(): LiveData<List<LineaView>>{
        return repository.getAllLineas()
    }

}

class EstacionViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: EstacionRepository = EstacionRepository(application)

    fun getEstacionesByLinea(linea:Int): LiveData<List<Estacion>>{
        return repository.getEstacionesByLinea(linea)
    }

    fun getCorrespondencias(estacion:String , linea:Int): List<Int> {
        return repository.getCorrespondencias(estacion, linea)
    }
}
