package com.example.revistasuteq.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.revistasuteq.R
import com.example.revistasuteq.models.Revistas
import com.google.android.material.chip.Chip

class RevistaAdapter(
    private val revistas: List<Revistas>,
    private val onItemClick: (Revistas) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = revistas.size
    override fun getItem(position: Int): Any = revistas[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_revista, parent, false)

        val revista = revistas[position]

        val imgRevista = view.findViewById<ImageView>(R.id.imgRevista)
        val txtTitulo = view.findViewById<TextView>(R.id.txtTitulo)
        val chipIssn = view.findViewById<Chip>(R.id.chipIssn)
        val txtDescripcion = view.findViewById<TextView>(R.id.txtDescripcion)
        val btnVerRevista = view.findViewById<Button>(R.id.btnVerRevista)

        txtTitulo.text = revista.titulo
        txtDescripcion.text = revista.descripcion

        if (revista.issn.isNullOrEmpty()) {
            chipIssn.visibility = View.GONE
        } else {
            chipIssn.visibility = View.VISIBLE
            chipIssn.text = "ISSN: ${revista.issn}"
        }

        Glide.with(view.context)
            .load(revista.imagen_url)
            .into(imgRevista)

        btnVerRevista.setOnClickListener { onItemClick(revista) }
        view.setOnClickListener { onItemClick(revista) }

        return view
    }
}