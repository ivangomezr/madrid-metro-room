package net.azarquiel.metroroom.model
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Linea::class, Estacion::class], version = 1)
abstract class MetroDB: RoomDatabase() {
    abstract fun lineaDao():LineaDao
    abstract fun estacionDao():EstacionDao
    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE:  MetroDB? = null

        fun getDatabase(context: Context): MetroDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MetroDB::class.java,   "MetroDB.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
