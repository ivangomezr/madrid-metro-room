package net.azarquiel.metroroom.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import net.azarquiel.metroroom.R
import net.azarquiel.metroroom.model.LineaView


class CustomAdapterLineas(
    val context: Context,
    val layout: Int
) : RecyclerView.Adapter<CustomAdapterLineas.ViewHolder>() {

    private var dataList: List<LineaView> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    internal fun setLineas(lineas: List<LineaView>) {
        this.dataList = lineas
        notifyDataSetChanged()
    }

    class ViewHolder(viewlayout: View,val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: LineaView){
            val tvLinea = itemView.findViewById<TextView>(R.id.tvLinea)
            val tvIniFin = itemView.findViewById<TextView>(R.id.tvIniFin)
            val ivLinea = itemView.findViewById<ImageView>(R.id.ivLinea)

            tvLinea.text = dataItem.nombre
            tvIniFin.text = dataItem.inifin
            val id = context.resources.getIdentifier("icono_linea_" + dataItem.id, "drawable", context.packageName)
            ivLinea.setImageResource(id)
            // La linea Ramal es blanca sobre icono blanco no se vería. Es por ello que lo ponemos grisaceo (#FFF0F0F0)
            (itemView as CardView).setCardBackgroundColor(Color.parseColor(dataItem.color.replace("#FFFFFFFF","#FFF0F0F0")))

            itemView.tag = dataItem
        }
    }
}
