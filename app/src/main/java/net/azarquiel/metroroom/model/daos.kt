package net.azarquiel.metroroom.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update

@Dao
interface LineaDao {
    @Query(
        "select l.id id, l.nombre nombre, l.color color, y.inifin inifin from " +
                "(select x.linea, x.mini, x.maxi, e1.nombre||' - '||e2.nombre inifin  " +
                "from (select linea, min(id) mini, max(id) maxi from estacion " +
                "group by linea) x, estacion e1, estacion e2 " +
                "where x.mini=e1.id and x.maxi=e2.id) y, linea l " +
                "where y.linea=l.id")
    fun getAllLineas(): LiveData<List<LineaView>>

}
@Dao
interface EstacionDao {
    @Query("select * from estacion where linea=:linea ORDER BY id ASC")
    fun getEstacionesByLinea(linea:Int): LiveData<List<Estacion>>

    @Query("SELECT linea from estacion WHERE nombre=:estacion and linea!=:linea")
    fun getCorrespondencias(estacion:String , linea:Int): List<Int>
}
