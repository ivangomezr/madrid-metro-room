package net.azarquiel.metroroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "linea")
data class Linea(@PrimaryKey
                    var id: Int=0,
                    var nombre:String="",
                    var color:String="")
@Entity(tableName = "estacion")
data class Estacion(@PrimaryKey
                 var id: Int=0,
                 var nombre:String="",
                 var linea:Int=0)
data class LineaView(@PrimaryKey
                var id: Int=0,
                var nombre:String="",
                var color:String="",
                var inifin:String=""):Serializable
data class EstacionView (var id: Int,
                         var nombre:String,
                         var correspondencias:List<Int>): Serializable