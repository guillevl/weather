package com.example.weather.climas

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class WeathersFragment() : Fragment(R.layout.fragment_weathers) {
    private lateinit var rvTiempo: RecyclerView
    private var adapter: WeathesAdapter? = null
    private val viewModel: WeatherViewModel by activityViewModels()
    // private lateinit var pbLoading: View


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTiempo = view.findViewById(R.id.rvTiempo)

        initList()
        listenEvents(view)
        viewModel.getTiempoActual()
        viewModel.getTiempo5Dias()
    }


    private fun initList() {
        val mLayoutManager = GridLayoutManager(context, 1)
        rvTiempo.layoutManager = mLayoutManager

        adapter = WeathesAdapter()
        rvTiempo.adapter = adapter
    }


    private fun listenEvents(view: View) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.tiempoActual.collect {
                        Log.i("GET", it.toString())
                        view.findViewById<TextView>(R.id.temp).text =
                            it.firstOrNull()?.main?.temp?.toString()
                        view.findViewById<TextView>(R.id.humedad).text =
                            "Humedad: " + it.firstOrNull()?.main?.humidity.toString() + "%"
                        view.findViewById<TextView>(R.id.location).text = it.firstOrNull()?.name
                        var ivIcono = view.findViewById<ImageView>(R.id.imagenHoy)
                        var imageUrlBase = "https://openweathermap.org/img/w/"
                        Picasso.get()
                            .load(imageUrlBase + it.firstOrNull()?.weather?.firstOrNull()?.icon + ".png")
                            .into(ivIcono)
                    }

                }
                launch {
                    viewModel.tiempo5Dias.collect {
                        if (it.isNotEmpty()) {
                            adapter?.data = it.first().list
                            adapter?.notifyDataSetChanged()
                        }
                    }
                }
            }
        }
    }
}