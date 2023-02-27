package com.example.weather.climas

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.data.models.Tiempo5DiasResponse
import com.squareup.picasso.Picasso
import java.util.*


class WeathesAdapter(var data: List<Tiempo5DiasResponse.WeatherItem> = listOf()) :
    RecyclerView.Adapter<WeathesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var datos = data.get(position)
        holder.bind(datos)
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val genero = itemView.findViewById<TextView>(R.id.temperatura)
        val fecha = itemView.findViewById<TextView>(R.id.fecha)
        val humedad = itemView.findViewById<TextView>(R.id.hmdad)
        val imagen5Dias = itemView.findViewById<ImageView>(R.id.ivIcon)
        fun bind(item: Tiempo5DiasResponse.WeatherItem) {
            var imageUrlBase = "https://openweathermap.org/img/w/"
            Picasso.get()
                .load(imageUrlBase + item.weather[0].icon + ".png")
                .into(imagen5Dias)
            genero.text = item.main.temp.toString()+ "ºC"
            humedad.text = "Humedad: "+item.main.humidity.toString()+"%"
            fecha.text = formatDate(item.dt_txt)
        }
    }
    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val outputFormat = SimpleDateFormat("d 'de' MMMM, HH:mm", Locale("es", "ES"))

        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    }
}
