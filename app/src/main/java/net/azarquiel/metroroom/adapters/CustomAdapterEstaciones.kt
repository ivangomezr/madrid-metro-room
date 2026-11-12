package net.azarquiel.recyclerclase.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.metroroom.R
import net.azarquiel.metroroom.model.EstacionView

class CustomAdapterEstaciones(
    val context: Context,
    val layout: Int,
    val color: String
) : RecyclerView.Adapter<CustomAdapterEstaciones.ViewHolder>() {

    private var dataList: List<EstacionView> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item,color)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setEstaciones(estaciones: List<EstacionView>) {
        this.dataList = estaciones
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: EstacionView, color: String){
            val tvEstacion = itemView.findViewById<TextView>(R.id.tvEstacion)
            val linearIconos = itemView.findViewById<LinearLayout>(R.id.linearIconos)
            tvEstacion.text = dataItem.nombre
            linearIconos.removeAllViews()
            for (correspondencia in dataItem.correspondencias){
                val iv = ImageView(context)
                val witdhiv = context.resources.getDimension(R.dimen.witdhiv)
                val layoutParams = LinearLayout.LayoutParams(witdhiv.toInt(),witdhiv.toInt())
                iv.layoutParams = layoutParams
                val id = context.resources.getIdentifier("icono_linea_" + correspondencia, "drawable", context.packageName)
                iv.setImageResource(id)
                linearIconos.addView(iv)
            }
            // Obtenemos un color igual que el original pero mas transparente, cambiando el FF por 66 en su opacidad
            // Los metros ligeros vienen con     opacidad A0, los ponemos mas transparentes cambiando a 44
            var newcolor = color.replace("#FF", "#66").replace("#A0", "#44")
            (itemView as CardView).setCardBackgroundColor(Color.parseColor(newcolor))
        }
    }
}