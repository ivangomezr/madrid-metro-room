package net.azarquiel.metroroom.model

import android.app.Application
import androidx.lifecycle.LiveData

class LineaRepository(application: Application) {

    val lineaDao = MetroDB.getDatabase(application)!!.lineaDao()
    fun getAllLineas(): LiveData<List<LineaView>> {
        return lineaDao.getAllLineas()
    }

}

class EstacionRepository(application: Application) {

    val estacionDao = MetroDB.getDatabase(application)!!.estacionDao()
    fun getEstacionesByLinea(linea:Int): LiveData<List<Estacion>> {
        return estacionDao.getEstacionesByLinea(linea)
    }
    fun getCorrespondencias(estacion:String , linea:Int): List<Int> {
        return estacionDao.getCorrespondencias(estacion, linea)
    }

}
