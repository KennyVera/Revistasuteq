package com.example.revistasuteq.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import com.example.revistasuteq.R
import com.example.revistasuteq.models.Volumens

class ArticuloAdapter(
    private val articulos: List<Volumens>,
    private val onDoiClick: (String) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = articulos.size
    override fun getItem(position: Int): Any = articulos[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_articulo, parent, false)

        val articulo = articulos[position]

        val txtTituloArticulo = view.findViewById<TextView>(R.id.txtTituloArticulo)
        val txtAutores = view.findViewById<TextView>(R.id.txtAutores)
        val btnDoi = view.findViewById<Button>(R.id.btnDoi)

        txtTituloArticulo.text = articulo.titulo
        txtAutores.text = "Autores: ${articulo.autores}"

        if (articulo.doi.isNullOrEmpty()) {
            btnDoi.isEnabled = false
            btnDoi.alpha = 0.5f
        } else {
            btnDoi.isEnabled = true
            btnDoi.alpha = 1.0f
            btnDoi.setOnClickListener { onDoiClick(articulo.doi) }
        }

        return view
    }
}