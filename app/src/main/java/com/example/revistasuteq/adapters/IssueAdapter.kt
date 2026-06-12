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
import com.example.revistasuteq.models.Issues

class IssueAdapter(
    private val issues: List<Issues>,
    private val onItemClick: (Issues) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = issues.size
    override fun getItem(position: Int): Any = issues[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_issue, parent, false)

        val issue = issues[position]

        val imgIssueCover = view.findViewById<ImageView>(R.id.imgIssueCover)
        val txtVolumenNum = view.findViewById<TextView>(R.id.txtVolumenNum)
        val txtTituloIssue = view.findViewById<TextView>(R.id.txtTituloIssue)
        val txtFechaPublicacion = view.findViewById<TextView>(R.id.txtFechaPublicacion)
        val btnVerArticulos = view.findViewById<Button>(R.id.btnVerArticulos)

        txtVolumenNum.text = "Vol. ${issue.volumen}, Núm. ${issue.numero} (${issue.year})"
        txtTituloIssue.text = issue.titulo
        
        if (issue.date_published.isNotEmpty()) {
            txtFechaPublicacion.visibility = View.VISIBLE
            txtFechaPublicacion.text = "Publicado: ${issue.date_published}"
        } else {
            txtFechaPublicacion.visibility = View.GONE
        }

        Glide.with(view.context)
            .load(issue.cover)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(imgIssueCover)

        btnVerArticulos.setOnClickListener { onItemClick(issue) }
        view.setOnClickListener { onItemClick(issue) }

        return view
    }
}